package ua.com.alevel.scheduler;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;
import ua.com.alevel.persistence.repository.sunglasses.SunglassesRepository;

import org.springframework.http.HttpHeaders;
import ua.com.alevel.scheduler.dto.SunglassesForCheckByPriceAndQtyDto;

import java.util.Arrays;
import java.util.List;

@Service
public class SupplierScheduler {

    @Value("${supplier.url}")
    private String supplierUrl;

    @Value("${supplier.auth.token}")
    private String supplierAuthToken;

    private final SunglassesRepository sunglassesRepository;

    public SupplierScheduler(SunglassesRepository sunglassesRepository) {
        this.sunglassesRepository = sunglassesRepository;
    }

    @Scheduled(cron = "0 * * ? * *")
    public void callToSupplier() {
        List<String> modelCodesList = sunglassesRepository.findAllModelsWhereQuantityIsZero();
        if (CollectionUtils.isNotEmpty(modelCodesList)) {
            StringBuilder queryBuilder = new StringBuilder("/api/sunglasses?modelCode=");
            for (String modelCode : modelCodesList) {
                queryBuilder.append(modelCode);
                queryBuilder.append(",");
            }
            final String url = supplierUrl + queryBuilder;
            final RestTemplate restTemplate = new RestTemplate();
            final HttpHeaders headers = new HttpHeaders();
            headers.add("Auth-token", supplierAuthToken);
            HttpEntity<SunglassesForCheckByPriceAndQtyDto[]> entity = new HttpEntity<>(headers);
            ResponseEntity<SunglassesForCheckByPriceAndQtyDto[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    SunglassesForCheckByPriceAndQtyDto[].class);
            if (response.getStatusCodeValue() == 200) {
                SunglassesForCheckByPriceAndQtyDto[] body = response.getBody();
                if (ArrayUtils.isNotEmpty(body)) {
                    List<SunglassesForCheckByPriceAndQtyDto> dtoList = Arrays.asList(body);
                    List<Sunglasses> sunglassesList = sunglassesRepository.findAllByModelCodeIn(modelCodesList);
                    for (Sunglasses sunglasses : sunglassesList) {
                        SunglassesForCheckByPriceAndQtyDto dto = dtoList
                                .stream()
                                .filter(sunglassesForCheckByPriceAndQtyDto -> sunglassesForCheckByPriceAndQtyDto.getModelCode().equals(sunglasses.getModelCode()))
                                .findFirst()
                                .get();
                        sunglasses.setPrice(dto.getPrice());
                        sunglasses.setQuantity(dto.getQuantity());
                    }
                    sunglassesRepository.saveAll(sunglassesList);
                }
            }
        }
    }
}