package ua.com.alevel.service;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.sunglasses.Color;

public interface ColorService {

    Color findByColorValue(String color);
}
