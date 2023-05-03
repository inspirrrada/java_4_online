package ua.com.alevel.facade.sunglasses.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import org.apache.commons.collections4.MapUtils;
import ua.com.alevel.data.response.SunglassesPDPDto;
import ua.com.alevel.data.response.SunglassesPLPDto;
import ua.com.alevel.facade.sunglasses.SunglassesFacade;
import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;
import ua.com.alevel.service.SunglassesService;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class SunglassesFacadeImpl implements SunglassesFacade {

    private final SunglassesService sunglassesService;

    public SunglassesFacadeImpl(SunglassesService sunglassesService) {
        this.sunglassesService = sunglassesService;
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
                String brandId = brands[0];
                return sunglassesService
                        .findByBrand(Long.parseLong(brandId))
                        .stream()
                        .map(SunglassesPLPDto::new)
                        .toList();
            }
            String[] frameShapes = map.get("frameShape");
            if (frameShapes != null) {
                String frameShapeId = frameShapes[0];
                return sunglassesService
                        .findByFrameShape(Long.parseLong(frameShapeId))
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
