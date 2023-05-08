package ua.com.alevel.persistence.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LensType {

    GRADIENT("Gradient"),
    MIRRORED("Mirrored"),
    POLARIZED("Polarized");

    private final String value;
}
