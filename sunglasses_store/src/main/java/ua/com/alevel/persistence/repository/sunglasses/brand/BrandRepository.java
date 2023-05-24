package ua.com.alevel.persistence.repository.sunglasses.brand;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.sunglasses.brand.Brand;
import ua.com.alevel.persistence.repository.BaseRepository;

@Repository
public interface BrandRepository extends BaseRepository<Brand> {

    Brand findByBrandName(String brandName);
}
