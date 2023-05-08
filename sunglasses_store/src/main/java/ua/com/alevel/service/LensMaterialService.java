package ua.com.alevel.service;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.sunglasses.LensMaterial;

public interface LensMaterialService {

    LensMaterial findByLensMaterialValue(String lensMaterialValue);
}
