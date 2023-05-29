package ua.com.alevel.repository.sunglasses.features;

import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.sunglasses.features.Color;
import ua.com.alevel.repository.BaseRepository;
import ua.com.alevel.type.sunglasses_features.ColorType;

@Repository
public interface ColorRepository extends BaseRepository<Color> {

    Color findByColorType(ColorType colorType);
}
