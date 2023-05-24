package ua.com.alevel.persistence.repository.sunglasses;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.sunglasses.brand.Brand;
import ua.com.alevel.persistence.entity.sunglasses.*;
import ua.com.alevel.persistence.entity.sunglasses.features.*;
import ua.com.alevel.persistence.repository.BaseRepository;

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
//    @Query("from Sunglasses s join s.carts carts_sunglasses where carts_sunglasses.id = :cartId")
//    List<Sunglasses> findByCart(Long cartId);

    List<Sunglasses> findAllByModelCodeIn(List<String> modelCodes);

    @Query("select s.modelCode from Sunglasses s where s.quantity = 0")
    List<String> findAllModelsWhereQuantityIsZero();
}
