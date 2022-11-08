package ricotunes.services.card.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ricotunes.services.card.dto.UserDto;
import ricotunes.services.card.exception.ResourceNotFoundException;
import ricotunes.services.card.model.User;
import ricotunes.services.card.repository.UserRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/v1/")
public class UserController {

    private UserRepository userRepository;
    private final SimpMessagingTemplate webSocket;

    public UserController(UserRepository userRepository,  SimpMessagingTemplate webSocket) {
        this.userRepository = userRepository;
        this.webSocket = webSocket;
    }

    //get all users
    @GetMapping("users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    //get user by Id
    @GetMapping("user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        return ResponseEntity.ok().body(user);
    }


    @PutMapping("user/{id}")
    @PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
    public User updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserDto userDto) throws ResourceNotFoundException {
        System.out.println("Update User with ID = " + id + "...");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + id));

        user.setEmail(userDto.getEmail());
        user.setFullname(userDto.getFullname());
        user.setUsername(userDto.getUsername());
        final User updatedUser = userRepository.save(user);
        System.out.println("Updated User " + updatedUser);
        return userRepository.save(updatedUser);
    }

    //delete user
    @DeleteMapping("user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + id));
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
