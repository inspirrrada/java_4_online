package ua.com.alevel.persistence.type.sunglasses_features;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LensType {

    GRADIENT("Gradient"),
    MIRRORED("Mirrored"),
    POLARIZED("Polarized"),
    ORDINARY("Ordinary");

    private final String value;
}
