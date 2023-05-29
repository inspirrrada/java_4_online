package ua.com.alevel.repository.sunglasses.features;

import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.sunglasses.features.FrameMaterial;
import ua.com.alevel.repository.BaseRepository;
import ua.com.alevel.type.sunglasses_features.FrameMaterialType;

@Repository
public interface FrameMaterialRepository extends BaseRepository<FrameMaterial> {

    FrameMaterial findByFrameMaterialType(FrameMaterialType frameMaterialType);
}
