package ua.com.alevel.repository.sunglasses.features;

import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.sunglasses.features.LensMaterial;
import ua.com.alevel.repository.BaseRepository;
import ua.com.alevel.type.sunglasses_features.LensMaterialType;

@Repository
public interface LensMaterialRepository extends BaseRepository<LensMaterial> {

    LensMaterial findByLensMaterialType(LensMaterialType lensMaterialType);
}
