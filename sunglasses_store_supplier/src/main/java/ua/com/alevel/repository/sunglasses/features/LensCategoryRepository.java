package ua.com.alevel.repository.sunglasses.features;

import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.sunglasses.features.LensCategory;
import ua.com.alevel.repository.BaseRepository;
import ua.com.alevel.type.sunglasses_features.LensType;

@Repository
public interface LensCategoryRepository extends BaseRepository<LensCategory> {

    LensCategory findByLensType(LensType lensType);
}
