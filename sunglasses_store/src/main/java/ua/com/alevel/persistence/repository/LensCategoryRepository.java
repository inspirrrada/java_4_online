package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.sunglasses.LensCategory;
import ua.com.alevel.persistence.type.LensType;

@Repository
public interface LensCategoryRepository extends BaseRepository<LensCategory> {

    LensCategory findByLensType(LensType lensType);
}
