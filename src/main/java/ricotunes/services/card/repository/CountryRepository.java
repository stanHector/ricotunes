package ricotunes.services.card.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ricotunes.services.card.model.Country;

@Repository
public interface CountryRepository  extends JpaRepository<Country, Long> {
}
