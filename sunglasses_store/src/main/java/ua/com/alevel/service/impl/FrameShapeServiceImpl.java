package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.sunglasses.FrameShape;
import ua.com.alevel.persistence.repository.FrameShapeRepository;
import ua.com.alevel.persistence.type.FrameShapeType;
import ua.com.alevel.service.FrameShapeService;

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
