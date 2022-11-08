package ricotunes.services.card.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ricotunes.services.card.dto.WalletDto;
import ricotunes.services.card.exception.ResourceNotFoundException;
import ricotunes.services.card.model.Wallet;
import ricotunes.services.card.repository.WalletRepository;
import ricotunes.services.card.service.WalletService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/v1/")
public class WalletController {
    private final WalletRepository walletRepository;

    private final WalletService walletService;

    public WalletController(WalletRepository walletRepository, WalletService walletService) {
        this.walletRepository = walletRepository;
        this.walletService = walletService;
    }

    //    get wallet by id
    @GetMapping("wallet/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Wallet> getWalletById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Wallet wallet = walletRepository.findByUserId(id);
        if (wallet == null) {
            throw new ResourceNotFoundException("Wallet not found for this id: " + id);
        }
        return ResponseEntity.ok().body(wallet);
    }

    @GetMapping("wallets")
    List<Wallet> getWallets() {
        return walletRepository.findAll();
    }

    @PutMapping("wallet/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Wallet updateWallet(@PathVariable("userId") Long userId, @Valid @RequestBody WalletDto walletDto) throws ResourceNotFoundException {
        System.out.println("Update Wallet with ID = " + userId + "...");
        Wallet wallet = walletRepository.findByUserId(userId);
        if (wallet == null) {
            throw new ResourceNotFoundException("Wallet not found for this userId: " + userId);
        } else {

            wallet.setCurrentBalance(walletDto.getCurrentBalance());
            final Wallet updatedWallet = walletRepository.save(wallet);
            System.out.println("Updated wallet " + updatedWallet);
            return walletRepository.save(updatedWallet);
        }
    }

    //    get wallet by user id
    @GetMapping("wallet/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Wallet> getWalletByUserId(@PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
        Wallet wallet = walletRepository.findByUserId(userId);
        if (wallet == null) {
            throw new ResourceNotFoundException("Wallet not found for this userId: " + userId);
        }
        return ResponseEntity.ok().body(wallet);
    }

//    @PostMapping("/api/wallet/{walletId}/account/{accountId}/withdraw/{amount}")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public ResponseEntity<ServiceResponse> withdraw(@PathVariable("walletId") long walletId, @PathVariable("accountId") long accountId, @PathVariable("amount") float amount) {
//        ServiceResponse response = new ServiceResponse();
//        try {
//            Account ac = walletService.withdrawFromAccount(walletId, accountId, amount, "WITHDRAW");
//            response.setStatus("200");
//            response.setDescription("Amount " + amount + " withdrawn successfully!!");
//            response.setData(ac);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//
//        } catch (WalletIdDoesNotExistException e) {
//            response.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
//            response.setDescription(e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        } catch (AccountNotAssociatedWithWalletException | InsufficientBalanceInWalletException e) {
//            response.setStatus(String.valueOf(HttpStatus.EXPECTATION_FAILED));
//            response.setDescription(e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
//        }
//
//    }

//    @PostMapping("/api/wallet/{walletId}/account/{accountId}/deposit/{amount}")
//    public ResponseEntity<ServiceResponse> deposit(@PathVariable("walletId") int walletId, @PathVariable("accountId") int accountId, @PathVariable("amount") float amount) {
//        ServiceResponse response = new ServiceResponse();
//        try {
//            Account ac = walletService.depositToAccount(walletId, accountId, amount, "DEPOSIT");
//            response.setStatus("200");
//            response.setDescription("Amount " + amount + " deposited successfully!!");
//            response.setData(ac);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//
//        } catch (WalletIdDoesNotExistException e) {
//            response.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
//            response.setDescription(e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        } catch (AccountNotAssociatedWithWalletException e) {
//            response.setStatus(String.valueOf(HttpStatus.EXPECTATION_FAILED));
//            response.setDescription(e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
//        }
//    }

    //    delete wallet
    @DeleteMapping("wallet/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found for this id: " + id));
        walletRepository.delete(wallet);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}