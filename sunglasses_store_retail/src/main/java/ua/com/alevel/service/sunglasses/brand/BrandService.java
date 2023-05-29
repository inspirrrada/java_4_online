package ua.com.alevel.service.sunglasses.brand;

import ua.com.alevel.persistence.entity.sunglasses.brand.Brand;

public interface BrandService {

    Brand findByBrandName(String brandName);
}
