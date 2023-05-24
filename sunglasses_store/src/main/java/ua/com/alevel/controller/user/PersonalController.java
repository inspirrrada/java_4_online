package ua.com.alevel.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.user.PersonalAddressDto;
import ua.com.alevel.dto.user.PersonalInfoDto;
import ua.com.alevel.dto.user.PersonalPasswordDto;
import ua.com.alevel.facade.user.PersonalFacade;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.util.SecurityUtil;

@Controller
@RequestMapping(path = "/personal")
@SessionAttributes
public class PersonalController {

    private final PersonalFacade personalFacade;

    public PersonalController(PersonalFacade personalFacade) {
        this.personalFacade = personalFacade;
    }

    @GetMapping("/home")
    public String home() {
        return "pages/personal/home";
    }

    @GetMapping("/account")
    public String getAccountInfo(Model model) {
        String email = SecurityUtil.getUsername();
        Personal personal = personalFacade.findByEmail(email);
//        model.addAttribute("personal", personal);
        PersonalInfoDto personalInfoDto = new PersonalInfoDto(personal);
        model.addAttribute("personalInfo", personalInfoDto);
        PersonalPasswordDto personalPasswordDto = new PersonalPasswordDto(personal);
        model.addAttribute("personalPassword", personalPasswordDto);
        PersonalAddressDto personalAddressDto = new PersonalAddressDto(personal);
        model.addAttribute("personalAddress", personalAddressDto);
//        System.out.println("personalInfoDto before: " + personalInfoDto);
//        Personal personalUpdated = personal;
//        PersonalPasswordDto personalPasswordDto = new PersonalPasswordDto(personal);
//        model.addAttribute("personalUpdated", personal);
//        model.addAttribute("personalPassword", personalPasswordDto);
//        System.out.println("personalUpdated before: " + personalUpdated);
        return "pages/personal/account";
    }

    @PostMapping("/account")
    public String changeAccountInfo(WebRequest webRequest,
                                    @ModelAttribute("personalInfo") PersonalInfoDto personalInfoDto,
                                    @ModelAttribute("personalPassword") PersonalPasswordDto personalPasswordDto,
                                    @ModelAttribute("personalAddress") PersonalAddressDto personalAddressDto) {
        System.out.println("personalInfoDto after: " + personalInfoDto);
        boolean successChange = personalFacade.changeInfo(personalInfoDto, personalPasswordDto, personalAddressDto, webRequest);
        if (successChange) {
            return "pages/personal/saving_successful";
        } else {
            return "pages/personal/saving_fail";
        }
    }
}
