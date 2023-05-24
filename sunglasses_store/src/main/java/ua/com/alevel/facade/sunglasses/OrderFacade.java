package ua.com.alevel.facade.sunglasses;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.data.response.OrderDetailsDto;
import ua.com.alevel.data.response.PersonalAddressDto;
import ua.com.alevel.data.response.PersonalInfoDto;
import ua.com.alevel.data.response.PersonalPasswordDto;

public interface OrderFacade {

    boolean saveOrderDetails(OrderDetailsDto orderDetailsDto);
}
