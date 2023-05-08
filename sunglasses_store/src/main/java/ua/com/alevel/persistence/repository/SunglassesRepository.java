package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.brand.Brand;
import ua.com.alevel.persistence.entity.sunglasses.*;

import java.util.List;

@Repository
public interface SunglassesRepository extends BaseRepository<Sunglasses> {

    List<Sunglasses> findByBrand(Brand brand);
    List<Sunglasses> findByColor(Color color);
    List<Sunglasses> findByFrameMaterial(FrameMaterial frameMaterial);
    List<Sunglasses> findByFrameShape(FrameShape frameShape);
    List<Sunglasses> findByLensCategory(LensCategory lensCategory);
    List<Sunglasses> findByLensMaterial(LensMaterial lensMaterial);
    List<Sunglasses> findBySexCategory(SexCategory sexCategory);
    List<Sunglasses> findByTempleLength(Integer templeLength);
    List<Sunglasses> findByBridgeWidth(Integer bridgeWidth);
    List<Sunglasses> findByLensWidth(Integer lensWidth);
    List<Sunglasses> findAllByModelCodeIn(List<String> modelCodes);

    @Query("select s.modelCode from Sunglasses s where s.quantity = 0")
    List<String> findAllModelsWhereQuantityIsZero();
}
