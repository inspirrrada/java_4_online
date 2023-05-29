package ua.com.alevel.service.sunglasses.features.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.sunglasses.features.FrameShape;
import ua.com.alevel.persistence.repository.sunglasses.features.FrameShapeRepository;
import ua.com.alevel.persistence.type.sunglasses_features.FrameShapeType;
import ua.com.alevel.service.sunglasses.features.FrameShapeService;

@Service
public class FrameShapeServiceImpl implements FrameShapeService {

    private final FrameShapeRepository frameShapeRepository;

    public FrameShapeServiceImpl(FrameShapeRepository frameShapeRepository) {
        this.frameShapeRepository = frameShapeRepository;
    }

    @Override
    public FrameShape findByFrameShapeValue(String frameShapeValue) {
        FrameShapeType frameShapeType = FrameShapeType.valueOf(frameShapeValue);
        return frameShapeRepository.findByFrameShapeType(frameShapeType);
    }
}
