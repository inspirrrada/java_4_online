package ua.com.alevel.service.sunglasses.features.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.sunglasses.features.Color;
import ua.com.alevel.persistence.repository.sunglasses.features.ColorRepository;
import ua.com.alevel.persistence.type.sunglasses_features.ColorType;
import ua.com.alevel.service.sunglasses.features.ColorService;

@Service
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    public ColorServiceImpl(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Override
    public Color findByColorValue(String color) {
        ColorType colorType = ColorType.valueOf(color);
        return colorRepository.findByColorType(colorType);
    }
}
