package mil.swf.jm_finalproject.repository;

import mil.swf.jm_finalproject.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItineraryRepository extends JpaRepository<Itinerary,Long> {
    List<Itinerary> findByUserInfo_Id(Long userId);
}
