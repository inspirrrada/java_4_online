package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SunglassesStoreRetailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SunglassesStoreRetailApplication.class, args);
    }
}
