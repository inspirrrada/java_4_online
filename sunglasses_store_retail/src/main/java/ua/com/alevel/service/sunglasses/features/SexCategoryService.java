package ua.com.alevel.service.sunglasses.features;

import ua.com.alevel.persistence.entity.sunglasses.features.SexCategory;

public interface SexCategoryService {

    SexCategory findBySexCategoryValue(String sexCategoryValue);
}
