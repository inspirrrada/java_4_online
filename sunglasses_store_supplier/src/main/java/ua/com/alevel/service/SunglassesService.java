package ua.com.alevel.service;

import ua.com.alevel.dto.SunglassesForCheckByPriceAndQtyDto;

import java.util.List;

public interface SunglassesService {

    List<SunglassesForCheckByPriceAndQtyDto> findSunglassesByModelCode(List<String> modelCodesList);
}
