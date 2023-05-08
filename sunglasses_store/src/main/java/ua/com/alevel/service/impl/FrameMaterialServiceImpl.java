package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.sunglasses.FrameMaterial;
import ua.com.alevel.persistence.repository.FrameMaterialRepository;
import ua.com.alevel.persistence.type.FrameMaterialType;
import ua.com.alevel.service.FrameMaterialService;

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
