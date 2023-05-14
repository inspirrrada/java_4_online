package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.data.response.PersonalInfoDto;
import ua.com.alevel.facade.user.PersonalFacade;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.util.SecurityUtil;

@Controller
@RequestMapping(path = "/personal")
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
        PersonalInfoDto personalInfoDto = new PersonalInfoDto(personal);
        model.addAttribute("personalInfo", personalInfoDto);
        System.out.println("personalInfoDto before: " + personalInfoDto);
        return "pages/personal/account";
    }

    @PostMapping("/account")
    public String changeAccountInfo(@ModelAttribute("personalInfo") PersonalInfoDto personalInfoDto) {
        System.out.println("personalInfoDto after: " + personalInfoDto);
        boolean successChange = personalFacade.changeInfo(personalInfoDto);
        if (successChange) {
            return "pages/personal/saving_successful";
        } else {
            return "pages/personal/saving_fail";
        }
    }
}
