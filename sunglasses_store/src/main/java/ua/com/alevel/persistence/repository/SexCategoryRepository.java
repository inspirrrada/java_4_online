package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.sunglasses.SexCategory;
import ua.com.alevel.persistence.type.SexType;

@Repository
public interface SexCategoryRepository extends BaseRepository<SexCategory> {

    SexCategory findBySexType(SexType sexType);
}
