package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.sunglasses.LensCategory;
import ua.com.alevel.persistence.repository.LensCategoryRepository;
import ua.com.alevel.persistence.type.LensType;
import ua.com.alevel.service.LensCategoryService;

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
