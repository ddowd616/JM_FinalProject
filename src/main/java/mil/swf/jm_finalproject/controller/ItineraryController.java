package mil.swf.jm_finalproject.controller;

import mil.swf.jm_finalproject.DTO.ItineraryDTO;
import mil.swf.jm_finalproject.service.ItineraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/itineraries")
public class ItineraryController {

    private final ItineraryService itineraryService;

    public ItineraryController(ItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    @PostMapping("/create")
    public ResponseEntity<ItineraryDTO> createItineraryEntry(@RequestBody ItineraryDTO itineraryDTO){
        return ResponseEntity.ok(itineraryService.createItineraryEntry(itineraryDTO));
    }


}
