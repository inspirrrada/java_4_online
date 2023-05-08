package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.brand.Brand;
import ua.com.alevel.persistence.repository.BrandRepository;
import ua.com.alevel.service.BrandService;

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
