package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.brand.Brand;
import ua.com.alevel.persistence.entity.sunglasses.FrameShape;
import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;

import java.util.List;

@Repository
public interface SunglassesRepository extends BaseRepository<Sunglasses> {

    List<Sunglasses> findByBrand(Brand brand);
    List<Sunglasses> findByFrameShape(FrameShape frameShape);
    List<Sunglasses> findAllByModelCodeIn(List<String> modelCodes);

    @Query("select s.modelCode from Sunglasses s where s.quantity = 0")
    List<String> findAllModelsWhereQuantityIsZero();
}
