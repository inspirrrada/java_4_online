package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SunglassesService {

    Optional<Sunglasses> findById(Long id);
    Collection<Sunglasses> findAll();
    List<Sunglasses> findByBrand(Long brandId);
    List<Sunglasses> findByFrameShape(Long frameShapeId);
}
