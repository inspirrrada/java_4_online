package ua.com.alevel.persistence.repository.sunglasses.features;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.sunglasses.features.LensCategory;
import ua.com.alevel.persistence.repository.BaseRepository;
import ua.com.alevel.persistence.type.sunglasses_features.LensType;

@Repository
public interface LensCategoryRepository extends BaseRepository<LensCategory> {

    LensCategory findByLensType(LensType lensType);
}
