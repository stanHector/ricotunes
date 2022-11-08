package ricotunes.services.card.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ricotunes.services.card.enums.RoleName;
import ricotunes.services.card.exception.AppException;
import ricotunes.services.card.model.Role;
import ricotunes.services.card.model.User;
import ricotunes.services.card.model.Wallet;
import ricotunes.services.card.payload.request.LoginRequest;
import ricotunes.services.card.payload.request.SignUpRequest;
import ricotunes.services.card.payload.response.ApiResponse;
import ricotunes.services.card.payload.response.JwtAuthenticationResponse;
import ricotunes.services.card.payload.response.UserResponse;
import ricotunes.services.card.repository.RoleRepository;
import ricotunes.services.card.repository.UserRepository;
import ricotunes.services.card.repository.WalletRepository;
import ricotunes.services.card.security.JwtTokenProvider;
import ricotunes.services.card.service.UserService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/auth")
public class LoginController {
    private static final String USER_ROLE_NOT_SET = "User role not set";

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    private final UserService userService;
    private final WalletRepository walletRepository;

    public LoginController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, UserService userService, WalletRepository walletRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.walletRepository = walletRepository;
    }


    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        User users = userRepository.findByEmail(loginRequest.getUsernameOrEmail());
        return ResponseEntity.ok(new JwtAuthenticationResponse("Bearer " + jwt, "Login Successful!", users.getId(), users.getUsername(), users.getFullname(), users.getPhone(), users.getEmail()));
//        return ResponseEntity.ok(new JwtAuthenticationResponse());
    }


    @Transactional
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        if (Boolean.TRUE.equals(userRepository.existsByPhone(signUpRequest.getPhone()))) {

            return new ResponseEntity<>(new ApiResponse(false, "Phone number is already taken", HttpStatus.CONFLICT), HttpStatus.OK);
        }

        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            return new ResponseEntity<>(new ApiResponse(false, "Email address is already taken", HttpStatus.CONFLICT), HttpStatus.OK);
        }

        String username = signUpRequest.getUsername().toLowerCase();
        String fullname = signUpRequest.getFullname().toLowerCase();
        String phone = signUpRequest.getPhone().toLowerCase();
        String email = signUpRequest.getEmail().toLowerCase();
        String password = passwordEncoder.encode(signUpRequest.getPassword());

        User user = new User(username, fullname, phone, email, password);

        List<Role> roles = new ArrayList<>();

        if (userRepository.count() == 0) {
            roles.add(roleRepository.findByRoleName(RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
        } else {
            roles.add(roleRepository.findByRoleName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
        }
        user.setRoles(roles);
        User createdUser = userRepository.save(user);


        // create wallet
        Wallet wallet = new Wallet();
        wallet.setId(wallet.getId());
        wallet.setCurrentBalance(0.00);
        wallet.setUserId(user.getId());
        Wallet createdWallet = walletRepository.save(wallet);

        UserResponse response = new UserResponse();
        response.setUser(createdUser);
        response.setWallet(createdWallet);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
