package ua.com.alevel.persistence.repository.sunglasses.features;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.sunglasses.features.LensMaterial;
import ua.com.alevel.persistence.repository.BaseRepository;
import ua.com.alevel.persistence.type.sunglasses_features.LensMaterialType;

@Repository
public interface LensMaterialRepository extends BaseRepository<LensMaterial> {

    LensMaterial findByLensMaterialType(LensMaterialType lensMaterialType);
}
