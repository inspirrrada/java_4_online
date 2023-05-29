package ua.com.alevel.service.sunglasses.features;

import ua.com.alevel.persistence.entity.sunglasses.features.FrameMaterial;

public interface FrameMaterialService {

    FrameMaterial findByFrameMaterialValue(String frameMaterialValue);
}
