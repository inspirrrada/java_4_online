package ua.com.alevel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.dto.SunglassesForCheckByPriceAndQtyDto;
import ua.com.alevel.facade.SunglassesFacade;

import java.util.List;

@RestController
@RequestMapping("/api/sunglasses")
public class SunglassesController {

    private final SunglassesFacade sunglassesFacade;

    public SunglassesController(SunglassesFacade sunglassesFacade) {
        this.sunglassesFacade = sunglassesFacade;
    }

    @GetMapping
    public ResponseEntity<List<SunglassesForCheckByPriceAndQtyDto>> findAllSunglasses(@RequestParam(name = "modelCode") String modelCodeList) {
        return ResponseEntity.ok(sunglassesFacade.findSunglassesByModelCode(modelCodeList));
    }
}
