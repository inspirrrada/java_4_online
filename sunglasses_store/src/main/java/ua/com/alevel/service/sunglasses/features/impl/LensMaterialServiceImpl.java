package ua.com.alevel.service.sunglasses.features.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.sunglasses.features.LensMaterial;
import ua.com.alevel.persistence.repository.sunglasses.features.LensMaterialRepository;
import ua.com.alevel.persistence.type.sunglasses_features.LensMaterialType;
import ua.com.alevel.service.sunglasses.features.LensMaterialService;

@Service
public class LensMaterialServiceImpl implements LensMaterialService {

    private final LensMaterialRepository lensMaterialRepository;

    public LensMaterialServiceImpl(LensMaterialRepository lensMaterialRepository) {
        this.lensMaterialRepository = lensMaterialRepository;
    }

    @Override
    public LensMaterial findByLensMaterialValue(String lensMaterialValue) {
        LensMaterialType lensMaterialType = LensMaterialType.valueOf(lensMaterialValue);
        return lensMaterialRepository.findByLensMaterialType(lensMaterialType);
    }
}
