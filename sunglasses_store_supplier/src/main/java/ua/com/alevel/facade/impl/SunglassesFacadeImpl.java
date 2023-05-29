package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.SunglassesForCheckByPriceAndQtyDto;
import ua.com.alevel.facade.SunglassesFacade;
import ua.com.alevel.service.SunglassesService;

import java.util.List;

@Service
public class SunglassesFacadeImpl implements SunglassesFacade {

    private final SunglassesService sunglassesService;

    public SunglassesFacadeImpl(SunglassesService sunglassesService) {
        this.sunglassesService = sunglassesService;
    }

    @Override
    public List<SunglassesForCheckByPriceAndQtyDto> findSunglassesByModelCode(String modelCodeList) {
        List<String> modelCodes = List.of(modelCodeList.split(","));
        return sunglassesService.findSunglassesByModelCode(modelCodes);
    }
}
