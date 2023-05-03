package ua.com.alevel.facade.sunglasses;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.data.response.SunglassesPDPDto;
import ua.com.alevel.data.response.SunglassesPLPDto;

import java.util.Collection;

public interface SunglassesFacade {

    SunglassesPDPDto findById(Long id);
    Collection<SunglassesPLPDto> findAll(WebRequest webRequest);
}
