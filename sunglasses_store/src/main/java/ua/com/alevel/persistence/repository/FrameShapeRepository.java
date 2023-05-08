package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.sunglasses.FrameShape;
import ua.com.alevel.persistence.type.FrameShapeType;

@Repository
public interface FrameShapeRepository extends BaseRepository<FrameShape> {

    FrameShape findByFrameShapeType(FrameShapeType frameShapeType);
}
