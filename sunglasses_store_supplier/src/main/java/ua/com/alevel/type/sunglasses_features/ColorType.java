package ua.com.alevel.type.sunglasses_features;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ColorType {

    GREEN("Green"),
    DARK_GREEN("Dark green"),
    BLUE("Blue"),
    DARK_BLUE("Dark blue"),
    PINK("Pink"),
    BROWN("Brown"),
    BLACK("Black"),
    PINK_GRADIENT_BLUE("Pink gradient blue"),
    BLUE_GRADIENT_GREY("Blue gradient grey"),
    CLEAR_GRADIENT_BROWN("Clear gradient brown"),
    CLEAR_GRADIENT_BLUE("Clear gradient blue"),
    DARK_GREY("Dark grey");

    private final String value;
}
