package ricotunes.services.card.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ricotunes.services.card.exception.AlreadyExistException;
import ricotunes.services.card.model.Bank;
import ricotunes.services.card.repository.BankRepository;
import ricotunes.services.card.service.BankService;

import java.util.List;

@AllArgsConstructor
@Service
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;


    @Override
    public ResponseEntity<Bank> addBank(Bank bank) throws AlreadyExistException {
        Bank bankName = bankRepository.findByName(bank.getName());
        Bank bankCode = bankRepository.findBybankCode(bank.getBankCode());
        if (bankName != null) {
            throw new AlreadyExistException(String.format("Bank with name %s already exist", bank.getName()));
        } else if (bankCode != null) {
            throw new AlreadyExistException(String.format("Bank with bank code %s already exist", bank.getBankCode()));
        } else {
            return new ResponseEntity<>(bankRepository.save(bank), HttpStatus.CREATED);
        }
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }
}
