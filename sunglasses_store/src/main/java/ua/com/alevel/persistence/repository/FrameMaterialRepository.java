package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.sunglasses.FrameMaterial;
import ua.com.alevel.persistence.type.FrameMaterialType;

@Repository
public interface FrameMaterialRepository extends BaseRepository<FrameMaterial> {

    FrameMaterial findByFrameMaterialType(FrameMaterialType frameMaterialType);
}
