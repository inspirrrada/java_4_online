package ua.com.alevel.service;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.sunglasses.FrameShape;

public interface FrameShapeService {

    FrameShape findByFrameShapeValue(String frameShapeValue);
}
