package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.sunglasses.LensMaterial;
import ua.com.alevel.persistence.repository.LensMaterialRepository;
import ua.com.alevel.persistence.type.LensMaterialType;
import ua.com.alevel.service.LensMaterialService;

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
