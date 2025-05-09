package mil.swf.jm_finalproject.service;

import mil.swf.jm_finalproject.DTO.ItineraryDTO;
import mil.swf.jm_finalproject.repository.CountryRepository;
import mil.swf.jm_finalproject.repository.ItineraryRepository;
import mil.swf.jm_finalproject.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class ItineraryService {

    private final ItineraryRepository itineraryRepository;

    private final UserInfoRepository userInfoRepository;

    private final CountryRepository countryRepository;

    public ItineraryService(ItineraryRepository itineraryRepository, UserInfoRepository userInfoRepository, CountryRepository countryRepository) {
        this.itineraryRepository = itineraryRepository;
        this.userInfoRepository = userInfoRepository;
        this.countryRepository = countryRepository;
    }

    public ItineraryDTO createItineraryEntry(ItineraryDTO dto){

    }
}
