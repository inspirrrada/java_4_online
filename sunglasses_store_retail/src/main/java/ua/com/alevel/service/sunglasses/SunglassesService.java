package ua.com.alevel.service.sunglasses;

import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SunglassesService {

    Optional<Sunglasses> findById(Long id);
    Collection<Sunglasses> findAll();
    List<Sunglasses> findByBrand(Long brandId);
    List<Sunglasses> findByColor(Long colorId);
    List<Sunglasses> findByFrameMaterial(Long frameMaterialId);
    List<Sunglasses> findByFrameShape(Long frameShapeId);
    List<Sunglasses> findByLensCategory(Long lensCategoryId);
    List<Sunglasses> findByLensMaterial(Long lensMaterialId);
    List<Sunglasses> findBySexCategory(Long sexCategoryId);
    List<Sunglasses> findByTempleLength(Integer templeLength);
    List<Sunglasses> findByBridgeWidth(Integer bridgeWidth);
    List<Sunglasses> findByLensWidth(Integer lensWidth);
}
