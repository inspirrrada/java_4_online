package ua.com.alevel.type.sunglasses_features;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FrameMaterialType {

    WOOD("Wood"),
    METAL("Metal"),
    PLASTIC("Plastic");

    private final String value;
}
