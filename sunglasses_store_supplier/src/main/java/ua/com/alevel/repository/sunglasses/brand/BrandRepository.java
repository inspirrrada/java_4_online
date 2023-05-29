package ua.com.alevel.repository.sunglasses.brand;

import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.sunglasses.brand.Brand;
import ua.com.alevel.repository.BaseRepository;

@Repository
public interface BrandRepository extends BaseRepository<Brand> {

    Brand findByBrandName(String brandName);
}
