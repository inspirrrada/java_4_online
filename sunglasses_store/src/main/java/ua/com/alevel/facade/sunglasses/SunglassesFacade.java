package ua.com.alevel.facade.sunglasses;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.sunglasses.SunglassesPDPDto;
import ua.com.alevel.dto.sunglasses.SunglassesPLPDto;

import java.util.Collection;

public interface SunglassesFacade {

    SunglassesPDPDto findById(Long id);
    Collection<SunglassesPLPDto> findAll(WebRequest webRequest);
}
