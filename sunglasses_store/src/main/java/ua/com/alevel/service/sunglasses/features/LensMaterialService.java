package ua.com.alevel.service.sunglasses.features;

import ua.com.alevel.persistence.entity.sunglasses.features.LensMaterial;

public interface LensMaterialService {

    LensMaterial findByLensMaterialValue(String lensMaterialValue);
}
