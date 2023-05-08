package ua.com.alevel.service;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.sunglasses.FrameMaterial;

public interface FrameMaterialService {

    FrameMaterial findByFrameMaterialValue(String frameMaterialValue);
}
