package ua.com.alevel.service.sunglasses.features.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.sunglasses.features.SexCategory;
import ua.com.alevel.persistence.repository.sunglasses.features.SexCategoryRepository;
import ua.com.alevel.persistence.type.sunglasses_features.SexType;
import ua.com.alevel.service.sunglasses.features.SexCategoryService;

@Service
public class SexCategoryServiceImpl implements SexCategoryService {

    private final SexCategoryRepository sexCategoryRepository;

    public SexCategoryServiceImpl(SexCategoryRepository sexCategoryRepository) {
        this.sexCategoryRepository = sexCategoryRepository;
    }

    @Override
    public SexCategory findBySexCategoryValue(String sexCategoryValue) {
        SexType sexType = SexType.valueOf(sexCategoryValue);
        return sexCategoryRepository.findBySexType(sexType);
    }
}
