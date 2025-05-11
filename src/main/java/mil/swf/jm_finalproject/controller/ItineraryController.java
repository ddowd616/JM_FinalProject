package mil.swf.jm_finalproject.controller;

import mil.swf.jm_finalproject.DTO.ItineraryDTO;
import mil.swf.jm_finalproject.service.ItineraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
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

    @GetMapping("/{id}")
    public ResponseEntity<ItineraryDTO> getItineraryById(@PathVariable Long id){
        ItineraryDTO dto = itineraryService.getItineraryEntryById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItineraryDTO> updateItinerary(@PathVariable Long id, @RequestBody ItineraryDTO dto){
        ItineraryDTO updated = itineraryService.updateItineraryEntry(id,dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItinerary(@PathVariable Long id){
        itineraryService.deleteItineraryEntry(id);
        return ResponseEntity.ok("Itinerary deleted successfully.");
    }

    @GetMapping
    public ResponseEntity<List<ItineraryDTO>> getItineraries(@RequestParam(required = false) Long userId) {
        List<ItineraryDTO> itineraries = itineraryService.getAllItineraries(userId);
        return ResponseEntity.ok(itineraries);
    }


}
