package ua.com.alevel.service;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.sunglasses.LensCategory;

public interface LensCategoryService {

    LensCategory findByLensCategoryValue(String lensCategoryValue);
}
