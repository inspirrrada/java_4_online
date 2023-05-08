package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.sunglasses.LensMaterial;
import ua.com.alevel.persistence.type.LensMaterialType;

@Repository
public interface LensMaterialRepository extends BaseRepository<LensMaterial> {

    LensMaterial findByLensMaterialType(LensMaterialType lensMaterialType);
}
