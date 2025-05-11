package mil.swf.jm_finalproject.controller;

import mil.swf.jm_finalproject.entity.CurrencyCode;
import mil.swf.jm_finalproject.service.CurrencyCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/currency-codes")
public class CurrencyCodeController {
    private final CurrencyCodeService service;

    public CurrencyCodeController(CurrencyCodeService service) {
        this.service = service;
    }

    @GetMapping
    public List<CurrencyCode> getAllCurrencyCodes(){
        return service.getAllCurrencyCodes();
    }

    @GetMapping("/{code}")
    public ResponseEntity<CurrencyCode> getCurrencyCodeByCode(@PathVariable String code){
        return service.getCurrencyCodeByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
