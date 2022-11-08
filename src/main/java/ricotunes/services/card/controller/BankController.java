package ricotunes.services.card.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ricotunes.services.card.model.Bank;
import ricotunes.services.card.service.BankService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class BankController {
    private final BankService bankService;

    @GetMapping("banks")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Bank> getBanks(){
        return bankService.getAllBanks();
    }

    @PostMapping("add-bank")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createBank(@RequestBody Bank bank){
        return bankService.addBank(bank);
    }
}
