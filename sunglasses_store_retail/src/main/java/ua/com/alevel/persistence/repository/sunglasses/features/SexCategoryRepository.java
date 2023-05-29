package ua.com.alevel.persistence.repository.sunglasses.features;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.sunglasses.features.SexCategory;
import ua.com.alevel.persistence.repository.BaseRepository;
import ua.com.alevel.persistence.type.sunglasses_features.SexType;

@Repository
public interface SexCategoryRepository extends BaseRepository<SexCategory> {

    SexCategory findBySexType(SexType sexType);
}
