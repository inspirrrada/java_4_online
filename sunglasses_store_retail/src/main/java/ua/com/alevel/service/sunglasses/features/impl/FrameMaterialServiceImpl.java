package ua.com.alevel.service.sunglasses.features.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.sunglasses.features.FrameMaterial;
import ua.com.alevel.persistence.repository.sunglasses.features.FrameMaterialRepository;
import ua.com.alevel.persistence.type.sunglasses_features.FrameMaterialType;
import ua.com.alevel.service.sunglasses.features.FrameMaterialService;

@Service
public class FrameMaterialServiceImpl implements FrameMaterialService {

    private final FrameMaterialRepository frameMaterialRepository;

    public FrameMaterialServiceImpl(FrameMaterialRepository frameMaterialRepository) {
        this.frameMaterialRepository = frameMaterialRepository;
    }

    @Override
    public FrameMaterial findByFrameMaterialValue(String frameMaterialValue) {
        FrameMaterialType frameMaterialType = FrameMaterialType.valueOf(frameMaterialValue);
        return frameMaterialRepository.findByFrameMaterialType(frameMaterialType);
    }
}
