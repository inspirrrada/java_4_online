package ua.com.alevel.persistence.repository.sunglasses.features;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.sunglasses.features.FrameShape;
import ua.com.alevel.persistence.repository.BaseRepository;
import ua.com.alevel.persistence.type.sunglasses_features.FrameShapeType;

@Repository
public interface FrameShapeRepository extends BaseRepository<FrameShape> {

    FrameShape findByFrameShapeType(FrameShapeType frameShapeType);
}
