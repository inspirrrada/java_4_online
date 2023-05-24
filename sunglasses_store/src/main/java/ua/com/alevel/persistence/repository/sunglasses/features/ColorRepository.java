package ua.com.alevel.persistence.repository.sunglasses.features;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.sunglasses.features.Color;
import ua.com.alevel.persistence.repository.BaseRepository;
import ua.com.alevel.persistence.type.sunglasses_features.ColorType;

@Repository
public interface ColorRepository extends BaseRepository<Color> {

    Color findByColorType(ColorType colorType);
}
