package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.sunglasses.Color;
import ua.com.alevel.persistence.type.ColorType;

@Repository
public interface ColorRepository extends BaseRepository<Color> {

    Color findByColorType(ColorType colorType);
}
