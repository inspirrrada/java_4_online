package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.brand.Brand;

@Repository
public interface BrandRepository extends BaseRepository<Brand> {

    Brand findByBrandName(String brandName);
}
