package mil.swf.jm_finalproject.repository;

import mil.swf.jm_finalproject.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItineraryRepository extends JpaRepository<Itinerary,Long> {
}
