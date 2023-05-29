package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.dto.SunglassesForCheckByPriceAndQtyDto;
import ua.com.alevel.repository.sunglasses.SunglassesRepository;
import ua.com.alevel.service.SunglassesService;

import java.util.List;

@Service
public class SunglassesServiceImpl implements SunglassesService {

    private final SunglassesRepository sunglassesRepository;

    public SunglassesServiceImpl(SunglassesRepository sunglassesRepository) {
        this.sunglassesRepository = sunglassesRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SunglassesForCheckByPriceAndQtyDto> findSunglassesByModelCode(List<String> modelCodesList) {
        return sunglassesRepository.findSunglassesByModelCode(modelCodesList);
    }
}
