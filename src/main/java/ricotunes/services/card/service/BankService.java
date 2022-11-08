package ricotunes.services.card.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ricotunes.services.card.exception.AlreadyExistException;
import ricotunes.services.card.model.Bank;

import java.util.List;

@Service
public interface BankService {
    ResponseEntity addBank(Bank bank) throws AlreadyExistException;

    List<Bank> getAllBanks();
}
