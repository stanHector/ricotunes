package ricotunes.services.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ricotunes.services.card.model.CardTransactions;
import ricotunes.services.card.model.GiftCard;

@Repository
public interface CardTransactionRepository extends JpaRepository<CardTransactions, Long> {
    CardTransactions findByGiftCard(GiftCard giftCard);

    CardTransactions findByUserId(Long userId);
}
