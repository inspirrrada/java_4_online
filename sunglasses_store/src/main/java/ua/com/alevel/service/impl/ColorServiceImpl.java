package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.sunglasses.Color;
import ua.com.alevel.persistence.repository.ColorRepository;
import ua.com.alevel.persistence.type.ColorType;
import ua.com.alevel.service.ColorService;

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
