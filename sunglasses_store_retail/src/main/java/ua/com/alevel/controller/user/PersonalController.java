package ua.com.alevel.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.user.PersonalAddressDto;
import ua.com.alevel.dto.user.PersonalInfoDto;
import ua.com.alevel.dto.user.PersonalOrdersDto;
import ua.com.alevel.dto.user.PersonalPasswordDto;
import ua.com.alevel.facade.order.OrderFacade;
import ua.com.alevel.facade.user.PersonalFacade;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.util.SecurityUtil;
import java.util.Set;

@Controller
@RequestMapping(path = "/personal")
@SessionAttributes
public class PersonalController {

    private final PersonalFacade personalFacade;
    private final OrderFacade orderFacade;

    public PersonalController(PersonalFacade personalFacade, OrderFacade orderFacade) {
        this.personalFacade = personalFacade;
        this.orderFacade = orderFacade;
    }

    @GetMapping("/home")
    public String home() {
        return "pages/personal/home";
    }

    @GetMapping("/account")
    public String getAccountInfo(Model model) {
        Personal currentUser = personalFacade.findByEmail(SecurityUtil.getUsername());
        PersonalInfoDto personalInfoDto = personalFacade.findPersonalInfo(currentUser);
        model.addAttribute("personalInfo", personalInfoDto);
        PersonalPasswordDto personalPasswordDto = personalFacade.findPersonalPassword(currentUser);
        model.addAttribute("personalPassword", personalPasswordDto);
        PersonalAddressDto personalAddressDto = personalFacade.findPersonalAddress(currentUser);
        model.addAttribute("personalAddress", personalAddressDto);
        Set<PersonalOrdersDto> personalOrders = orderFacade.findAllOrdersByUser(currentUser);
        model.addAttribute("personalOrders", personalOrders);
        return "pages/personal/account";
    }

    @PostMapping("/account")
    public String changeAccountInfo(WebRequest webRequest,
                                    @ModelAttribute("personalInfo") PersonalInfoDto personalInfoDto,
                                    @ModelAttribute("personalPassword") PersonalPasswordDto personalPasswordDto,
                                    @ModelAttribute("personalAddress") PersonalAddressDto personalAddressDto) {
        boolean successChange = personalFacade.changeInfo(personalInfoDto, personalPasswordDto, personalAddressDto, webRequest);
        if (successChange) {
            return "pages/personal/saving_successful";
        } else {
            return "pages/personal/saving_fail";
        }
    }
}
