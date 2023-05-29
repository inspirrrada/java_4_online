package ua.com.alevel.service.sunglasses.features;

import ua.com.alevel.persistence.entity.sunglasses.features.Color;

public interface ColorService {

    Color findByColorValue(String color);
}
