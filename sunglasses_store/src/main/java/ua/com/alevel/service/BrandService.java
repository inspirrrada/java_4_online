package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.brand.Brand;

public interface BrandService {

    Brand findByBrandName(String brandName);
}
