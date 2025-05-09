package mil.swf.jm_finalproject.service;

import mil.swf.jm_finalproject.DTO.ItineraryDTO;
import mil.swf.jm_finalproject.entity.Country;
import mil.swf.jm_finalproject.entity.Itinerary;
import mil.swf.jm_finalproject.entity.UserInfo;
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
        ItineraryDTO ReqRes = new ItineraryDTO();
        UserInfo user = userInfoRepository.findById(dto.getUserId())
                .orElseThrow(()-> new RuntimeException("User not found"));
        Country country = countryRepository.findById(dto.getCountryId())
                .orElseThrow(()-> new RuntimeException("Country not found"));
        Itinerary itineraryEntry = new Itinerary(
                user,
                country,
                dto.getOrderOnTrip(),
                dto.getCountryOfOrigin(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getDaysSpentInCountry(),
                dto.getUserWantsCurrencyExchangeRate()
        );

    }
}
