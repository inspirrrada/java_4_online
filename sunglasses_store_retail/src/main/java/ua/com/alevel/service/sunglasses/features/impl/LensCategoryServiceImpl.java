package ua.com.alevel.service.sunglasses.features.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.sunglasses.features.LensCategory;
import ua.com.alevel.persistence.repository.sunglasses.features.LensCategoryRepository;
import ua.com.alevel.persistence.type.sunglasses_features.LensType;
import ua.com.alevel.service.sunglasses.features.LensCategoryService;

@Service
public class LensCategoryServiceImpl implements LensCategoryService {

    private final LensCategoryRepository lensCategoryRepository;

    public LensCategoryServiceImpl(LensCategoryRepository lensCategoryRepository) {
        this.lensCategoryRepository = lensCategoryRepository;
    }

    @Override
    public LensCategory findByLensCategoryValue(String lensCategoryValue) {
        LensType lensType = LensType.valueOf(lensCategoryValue);
        return lensCategoryRepository.findByLensType(lensType);
    }
}
