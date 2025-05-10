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

        Itinerary saveEntry = itineraryRepository.save(itineraryEntry);

        ReqRes.setId(saveEntry.getId());
        ReqRes.setUserId(saveEntry.getUserInfo().getUserId());
        ReqRes.setCountryId(saveEntry.getCountry().getCountryId());
        ReqRes.setOrderOnTrip(saveEntry.getOrderOnTrip());
        ReqRes.setCountryOfOrigin(saveEntry.getCountryOfOrigin());
        ReqRes.setStartDate(saveEntry.getStartDate());
        ReqRes.setEndDate(saveEntry.getEndDate());
        ReqRes.setDaysSpentInCountry(saveEntry.getDaysSpentInCountry());
        ReqRes.setUserWantsCurrencyExchangeRate(saveEntry.getUserWantsCurrencyExchangeRate());

        return ReqRes;

    }

    public ItineraryDTO getItineraryEntryById(Long id) {
        Itinerary itinerary = itineraryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Itinerary Entry not found"));

        ItineraryDTO dto = new ItineraryDTO();
        dto.setId(itinerary.getId());
        dto.setUserId(itinerary.getUserInfo().getUserId());
        dto.setCountryId(itinerary.getCountry().getCountryId());
        dto.setOrderOnTrip(itinerary.getOrderOnTrip());
        dto.setCountryOfOrigin(itinerary.getCountryOfOrigin());
        dto.setStartDate(itinerary.getStartDate());
        dto.setEndDate(itinerary.getEndDate());
        dto.setDaysSpentInCountry(itinerary.getDaysSpentInCountry());
        dto.setUserWantsCurrencyExchangeRate(itinerary.getUserWantsCurrencyExchangeRate());

        return dto;

    }
}
