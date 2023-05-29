package ua.com.alevel.type.sunglasses_features;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LensMaterialType {

    MINERAL("Mineral"),
    POLYMERIC("Polymeric");

    private final String value;
}
