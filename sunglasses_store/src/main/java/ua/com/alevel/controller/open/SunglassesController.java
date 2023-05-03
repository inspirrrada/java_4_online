package ua.com.alevel.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.sunglasses.SunglassesFacade;

@Controller
@RequestMapping("/sunglasses")
public class SunglassesController {

    private final SunglassesFacade sunglassesFacade;

    public SunglassesController(SunglassesFacade sunglassesFacade) {
        this.sunglassesFacade = sunglassesFacade;
    }

    @GetMapping
    public String plp(Model model, WebRequest webRequest) {
        model.addAttribute("sunglassesList", sunglassesFacade.findAll(webRequest));
        return "pages/open/sunglasses_plp";
    }

    public String pdp(Model model, @PathVariable Long id) {
        model.addAttribute("sunglasses", sunglassesFacade.findById(id));
        return "pages/open/sunglasses_pdp";
    }
}
