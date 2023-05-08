package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.sunglasses.SexCategory;
import ua.com.alevel.persistence.repository.SexCategoryRepository;
import ua.com.alevel.persistence.type.SexType;
import ua.com.alevel.service.SexCategoryService;

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
