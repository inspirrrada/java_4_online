package ua.com.alevel.repository.sunglasses.features;

import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.sunglasses.features.SexCategory;
import ua.com.alevel.repository.BaseRepository;
import ua.com.alevel.type.sunglasses_features.SexType;

@Repository
public interface SexCategoryRepository extends BaseRepository<SexCategory> {

    SexCategory findBySexType(SexType sexType);
}
