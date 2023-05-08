package ua.com.alevel.persistence.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FrameShapeType {

    RECTANGLE("Rectangle"),
    SQUARE("Square"),
    ROUND("Round"),
    GEOMETRIC("Geometric"),
    AVIATOR("Aviator"),
    CAT_EYE("Cat eye"),
    PANTO("Panto"),
    BUTTERFLY("Butterfly"),
    MASK("Mask");

    private final String value;
}
