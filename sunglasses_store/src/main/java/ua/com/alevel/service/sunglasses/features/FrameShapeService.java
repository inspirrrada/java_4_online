package ua.com.alevel.service.sunglasses.features;

import ua.com.alevel.persistence.entity.sunglasses.features.FrameShape;

public interface FrameShapeService {

    FrameShape findByFrameShapeValue(String frameShapeValue);
}
