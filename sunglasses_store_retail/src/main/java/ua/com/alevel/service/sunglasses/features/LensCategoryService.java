package ua.com.alevel.service.sunglasses.features;

import ua.com.alevel.persistence.entity.sunglasses.features.LensCategory;

public interface LensCategoryService {

    LensCategory findByLensCategoryValue(String lensCategoryValue);
}
