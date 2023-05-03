package ua.com.alevel.listener;

import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.math.BigDecimal;

public class SunglassesVisibleGenerationListener {

    @PostLoad
    @PostPersist
    @PostUpdate
    public void generateSunglassesVisible(Sunglasses sunglasses) {
        sunglasses.setVisible(sunglasses.getQuantity() != null &&
                        sunglasses.getQuantity() > 0 &&
                        sunglasses.getPrice() != null &&
                        sunglasses.getPrice().compareTo(new BigDecimal("00.00")) > 0);
    }
}
