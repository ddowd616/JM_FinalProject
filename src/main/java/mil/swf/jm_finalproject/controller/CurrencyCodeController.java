package mil.swf.jm_finalproject.controller;

import mil.swf.jm_finalproject.entity.CurrencyCode;
import mil.swf.jm_finalproject.service.CurrencyCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
