package ua.com.alevel.service.sunglasses.brand.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.sunglasses.brand.Brand;
import ua.com.alevel.persistence.repository.sunglasses.brand.BrandRepository;
import ua.com.alevel.service.sunglasses.brand.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand findByBrandName(String brandName) {
        return brandRepository.findByBrandName(brandName);
    }
}
