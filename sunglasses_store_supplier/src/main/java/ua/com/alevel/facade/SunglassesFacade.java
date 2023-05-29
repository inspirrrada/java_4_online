package ua.com.alevel.facade;

import ua.com.alevel.dto.SunglassesForCheckByPriceAndQtyDto;

import java.util.List;

public interface SunglassesFacade {

    List<SunglassesForCheckByPriceAndQtyDto> findSunglassesByModelCode(String modelCodeList);
}
