package ua.com.alevel.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.alevel.config.security.SecurityService;
import ua.com.alevel.dto.user.AuthData;
import ua.com.alevel.persistence.type.user.RoleType;
import ua.com.alevel.service.user.RegistrationService;
import ua.com.alevel.util.SecurityUtil;

@Controller
public class AuthController {

    private final SecurityService securityService;
    private  final RegistrationService registrationService;

    public AuthController(SecurityService securityService, RegistrationService registrationService) {
        this.securityService = securityService;
        this.registrationService = registrationService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/index";
    }

    @GetMapping("/home")
    public String home() {
        return redirectToPageByRole();
    }

    @GetMapping("/login")
    public String login() {
        if (securityService.isAuthenticated()) {
            return redirectToPageByRole();
        }
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("authForm", new AuthData());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute AuthData authData) {
        registrationService.registration(authData);
        securityService.autoLogin(authData.getEmail(), authData.getPassword());
        return redirectToPageByRole();
    }

    private String redirectToPageByRole() {
        if (SecurityUtil.hasRole(RoleType.ROLE_PERSONAL.name())) {
            return "redirect:/personal/home";
        }
        if (SecurityUtil.hasRole(RoleType.ROLE_ADMIN.name())) {
            return "redirect:/admin/home";
        }
        return "/login";
    }
}
