package ua.com.alevel.persistence.repository.sunglasses.features;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.sunglasses.features.FrameMaterial;
import ua.com.alevel.persistence.repository.BaseRepository;
import ua.com.alevel.persistence.type.sunglasses_features.FrameMaterialType;

@Repository
public interface FrameMaterialRepository extends BaseRepository<FrameMaterial> {

    FrameMaterial findByFrameMaterialType(FrameMaterialType frameMaterialType);
}
