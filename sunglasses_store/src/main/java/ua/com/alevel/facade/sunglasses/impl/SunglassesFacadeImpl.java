package ua.com.alevel.facade.sunglasses.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import org.apache.commons.collections4.MapUtils;
import ua.com.alevel.dto.sunglasses.SunglassesPDPDto;
import ua.com.alevel.dto.sunglasses.SunglassesPLPDto;
import ua.com.alevel.facade.sunglasses.SunglassesFacade;
import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;
import ua.com.alevel.service.sunglasses.SunglassesService;
import ua.com.alevel.service.sunglasses.brand.BrandService;
import ua.com.alevel.service.sunglasses.features.*;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class SunglassesFacadeImpl implements SunglassesFacade {

    private final SunglassesService sunglassesService;
    private final BrandService brandService;
    private final ColorService colorService;
    private final FrameMaterialService frameMaterialService;
    private final FrameShapeService frameShapeService;
    private final LensCategoryService lensCategoryService;
    private final LensMaterialService lensMaterialService;
    private final SexCategoryService sexCategoryService;

    public SunglassesFacadeImpl(SunglassesService sunglassesService, BrandService brandService, ColorService colorService, FrameMaterialService frameMaterialService, FrameShapeService frameShapeService, LensCategoryService lensCategoryService, LensMaterialService lensMaterialService, SexCategoryService sexCategoryService) {
        this.sunglassesService = sunglassesService;
        this.brandService = brandService;
        this.colorService = colorService;
        this.frameMaterialService = frameMaterialService;
        this.frameShapeService = frameShapeService;
        this.lensCategoryService = lensCategoryService;
        this.lensMaterialService = lensMaterialService;
        this.sexCategoryService = sexCategoryService;
    }

    @Override
    public SunglassesPDPDto findById(Long id) {
        Optional<Sunglasses> optionalSunglasses = sunglassesService.findById(id);
        if (optionalSunglasses.isEmpty()) {
            throw new RuntimeException("Sunglasses not found");
        }
        Sunglasses sunglasses = optionalSunglasses.get();
        return new SunglassesPDPDto(sunglasses);
    }

    @Override
    public Collection<SunglassesPLPDto> findAll(WebRequest webRequest) {
        Map<String, String[]> map = webRequest.getParameterMap();
        if (MapUtils.isNotEmpty(map)) {
            String[] brands = map.get("brand");
            if (brands != null) {
                String brandName = brands[0];
                Long brandId = brandService.findByBrandName(brandName).getId();
                return sunglassesService
                        .findByBrand(brandId)
                        .stream()
                        .map(SunglassesPLPDto::new)
                        .toList();
            }
            String[] colors = map.get("color");
            if (colors != null) {
                String colorValue = colors[0];
                Long colorId = colorService.findByColorValue(colorValue).getId();
                return sunglassesService
                        .findByColor(colorId)
                        .stream()
                        .map(SunglassesPLPDto::new)
                        .toList();
            }
            String[] frameMaterials = map.get("frameMaterial");
            if (frameMaterials != null) {
                String frameMaterialValue = frameMaterials[0];
                Long frameMaterialId = frameMaterialService.findByFrameMaterialValue(frameMaterialValue).getId();
                return sunglassesService
                        .findByFrameMaterial(frameMaterialId)
                        .stream()
                        .map(SunglassesPLPDto::new)
                        .toList();
            }
            String[] frameShapes = map.get("frameShape");
            if (frameShapes != null) {
                String frameShapeValue = frameShapes[0];
                Long frameShapeId = frameShapeService.findByFrameShapeValue(frameShapeValue).getId();
                return sunglassesService
                        .findByFrameShape(frameShapeId)
                        .stream()
                        .map(SunglassesPLPDto::new)
                        .toList();
            }
            String[] lensCategories = map.get("lensCategory");
            if (lensCategories != null) {
                String lensCategoryValue = lensCategories[0];
                Long lensCategoryId = lensCategoryService.findByLensCategoryValue(lensCategoryValue).getId();
                return sunglassesService
                        .findByLensCategory(lensCategoryId)
                        .stream()
                        .map(SunglassesPLPDto::new)
                        .toList();
            }
            String[] lensMaterials = map.get("lensMaterial");
            if (lensMaterials != null) {
                String lensMaterial = lensMaterials[0];
                Long lensMaterialId = lensMaterialService.findByLensMaterialValue(lensMaterial).getId();
                return sunglassesService
                        .findByLensMaterial(lensMaterialId)
                        .stream()
                        .map(SunglassesPLPDto::new)
                        .toList();
            }
            String[] sexCategories = map.get("sexCategory");
            if (sexCategories != null) {
                String sexCategoryValue = sexCategories[0];
                Long sexCategoryId = sexCategoryService.findBySexCategoryValue(sexCategoryValue).getId();
                return sunglassesService
                        .findBySexCategory(sexCategoryId)
                        .stream()
                        .map(SunglassesPLPDto::new)
                        .toList();
            }
        }
        Collection<Sunglasses> sunglasses = sunglassesService.findAll();
        return sunglasses
                .stream()
                .map(SunglassesPLPDto::new)
                .toList();
    }
}
