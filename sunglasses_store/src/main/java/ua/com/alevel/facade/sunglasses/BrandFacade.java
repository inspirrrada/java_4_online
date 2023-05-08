package ua.com.alevel.facade.sunglasses;

import ua.com.alevel.persistence.entity.brand.Brand;

public interface BrandFacade {

    Brand findByBrandName(String brandName);
}
