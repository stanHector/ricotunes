package ricotunes.services.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ricotunes.services.card.model.Country;
import ricotunes.services.card.model.GiftCard;

@Repository
public interface GiftCardRepository extends JpaRepository<GiftCard,Long>{

    GiftCard findByCountry(Country currency);
}
