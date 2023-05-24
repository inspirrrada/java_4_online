package ua.com.alevel.persistence.type.sunglasses_features;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SexType {

    MEN("Men"),
    WOMEN("Women"),
    KIDS("Kids"),
    UNISEX("Unisex");

    private final String value;
}
