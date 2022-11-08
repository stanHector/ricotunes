package ricotunes.services.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ricotunes.services.card.model.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    Bank findByName(String name);
    Bank findBybankCode(String bankCode);
}
