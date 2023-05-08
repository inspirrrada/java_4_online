package ua.com.alevel.service;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.sunglasses.SexCategory;

public interface SexCategoryService {

    SexCategory findBySexCategoryValue(String sexCategoryValue);
}
