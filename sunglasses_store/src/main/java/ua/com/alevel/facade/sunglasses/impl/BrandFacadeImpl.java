package ua.com.alevel.facade.sunglasses.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.sunglasses.BrandFacade;
import ua.com.alevel.persistence.entity.brand.Brand;
import ua.com.alevel.service.BrandService;

@Service
public class BrandFacadeImpl implements BrandFacade {

    private final BrandService brandService;

    public BrandFacadeImpl(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public Brand findByBrandName(String brandName) {
        return brandService.findByBrandName(brandName);
    }
}
