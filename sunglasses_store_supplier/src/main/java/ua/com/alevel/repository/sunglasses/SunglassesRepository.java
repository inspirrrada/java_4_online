package ua.com.alevel.repository.sunglasses;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.dto.SunglassesForCheckByPriceAndQtyDto;
import ua.com.alevel.entity.sunglasses.Sunglasses;
import ua.com.alevel.repository.BaseRepository;

import java.util.List;

@Repository
public interface SunglassesRepository extends BaseRepository<Sunglasses> {

    @Query("select new ua.com.alevel.dto.SunglassesForCheckByPriceAndQtyDto(s.modelCode, s.price, s.quantity) from Sunglasses s where s.modelCode in :modelCodeList")
    List<SunglassesForCheckByPriceAndQtyDto> findSunglassesByModelCode(@Param("modelCodeList") List<String> modelCodeList);
}
