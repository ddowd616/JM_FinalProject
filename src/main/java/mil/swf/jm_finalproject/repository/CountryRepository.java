package mil.swf.jm_finalproject.repository;

import mil.swf.jm_finalproject.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CountryRepository extends JpaRepository<Country,Long> {
}
