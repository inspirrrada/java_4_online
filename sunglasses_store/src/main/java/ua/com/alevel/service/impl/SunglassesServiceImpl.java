package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.brand.Brand;
import ua.com.alevel.persistence.entity.sunglasses.FrameShape;
import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;
import ua.com.alevel.persistence.repository.BrandRepository;
import ua.com.alevel.persistence.repository.FrameShapeRepository;
import ua.com.alevel.persistence.repository.SunglassesRepository;
import ua.com.alevel.service.SunglassesService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class SunglassesServiceImpl implements SunglassesService {

    private final SunglassesRepository sunglassesRepository;
    private final FrameShapeRepository frameShapeRepository;
    private final BrandRepository brandRepository;

    public SunglassesServiceImpl(SunglassesRepository sunglassesRepository, FrameShapeRepository frameShapeRepository, BrandRepository brandRepository) {
        this.sunglassesRepository = sunglassesRepository;
        this.frameShapeRepository = frameShapeRepository;
        this.brandRepository = brandRepository;
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
    public List<Sunglasses> findByFrameShape(Long frameShapeId) {
        FrameShape frameShape = frameShapeRepository.findById(frameShapeId).get();
        return sunglassesRepository.findByFrameShape(frameShape);
    }
}
