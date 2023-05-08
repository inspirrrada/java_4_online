package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.brand.Brand;
import ua.com.alevel.persistence.entity.sunglasses.*;
import ua.com.alevel.persistence.repository.*;
import ua.com.alevel.service.SunglassesService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class SunglassesServiceImpl implements SunglassesService {

    private final SunglassesRepository sunglassesRepository;
    private final BrandRepository brandRepository;
    private final ColorRepository colorRepository;
    private final FrameMaterialRepository frameMaterialRepository;
    private final FrameShapeRepository frameShapeRepository;
    private final LensCategoryRepository lensCategoryRepository;
    private final LensMaterialRepository lensMaterialRepository;
    private final SexCategoryRepository sexCategoryRepository;

    public SunglassesServiceImpl(SunglassesRepository sunglassesRepository, FrameShapeRepository frameShapeRepository, BrandRepository brandRepository, ColorRepository colorRepository, FrameMaterialRepository frameMaterialRepository, LensCategoryRepository lensCategoryRepository, LensMaterialRepository lensMaterialRepository, SexCategoryRepository sexCategoryRepository) {
        this.sunglassesRepository = sunglassesRepository;
        this.frameShapeRepository = frameShapeRepository;
        this.brandRepository = brandRepository;
        this.colorRepository = colorRepository;
        this.frameMaterialRepository = frameMaterialRepository;
        this.lensCategoryRepository = lensCategoryRepository;
        this.lensMaterialRepository = lensMaterialRepository;
        this.sexCategoryRepository = sexCategoryRepository;
    }

    @Override
    public Optional<Sunglasses> findById(Long id) {
        return sunglassesRepository.findById(id);
    }

    @Override
    public Collection<Sunglasses> findAll() {
        return sunglassesRepository.findAll();
    }

    @Override
    public List<Sunglasses> findByBrand(Long brandId) {
        Brand brand = brandRepository.findById(brandId).get();
        return sunglassesRepository.findByBrand(brand);
    }

    @Override
    public List<Sunglasses> findByColor(Long colorId) {
        Color color = colorRepository.findById(colorId).get();
        return sunglassesRepository.findByColor(color);
    }

    @Override
    public List<Sunglasses> findByFrameMaterial(Long frameMaterialId) {
        FrameMaterial frameMaterial = frameMaterialRepository.findById(frameMaterialId).get();
        return sunglassesRepository.findByFrameMaterial(frameMaterial);
    }

    @Override
    public List<Sunglasses> findByFrameShape(Long frameShapeId) {
        FrameShape frameShape = frameShapeRepository.findById(frameShapeId).get();
        return sunglassesRepository.findByFrameShape(frameShape);
    }

    @Override
    public List<Sunglasses> findByLensCategory(Long lensCategoryId) {
        LensCategory lensCategory = lensCategoryRepository.findById(lensCategoryId).get();
        return sunglassesRepository.findByLensCategory(lensCategory);
    }

    @Override
    public List<Sunglasses> findByLensMaterial(Long lensMaterialId) {
        LensMaterial lensMaterial = lensMaterialRepository.findById(lensMaterialId).get();
        return sunglassesRepository.findByLensMaterial(lensMaterial);
    }

    @Override
    public List<Sunglasses> findBySexCategory(Long sexCategoryId) {
        SexCategory sexCategory = sexCategoryRepository.findById(sexCategoryId).get();
        return sunglassesRepository.findBySexCategory(sexCategory);
    }

    @Override
    public List<Sunglasses> findByTempleLength(Integer templeLength) {
        return sunglassesRepository.findByTempleLength(templeLength);
    }

    @Override
    public List<Sunglasses> findByBridgeWidth(Integer bridgeWidth) {
        return sunglassesRepository.findByBridgeWidth(bridgeWidth);
    }

    @Override
    public List<Sunglasses> findByLensWidth(Integer lensWidth) {
        return sunglassesRepository.findByLensWidth(lensWidth);
    }
}
