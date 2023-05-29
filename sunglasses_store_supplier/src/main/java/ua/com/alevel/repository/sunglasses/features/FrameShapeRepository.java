package ua.com.alevel.repository.sunglasses.features;

import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.sunglasses.features.FrameShape;
import ua.com.alevel.repository.BaseRepository;
import ua.com.alevel.type.sunglasses_features.FrameShapeType;

@Repository
public interface FrameShapeRepository extends BaseRepository<FrameShape> {

    FrameShape findByFrameShapeType(FrameShapeType frameShapeType);
}
