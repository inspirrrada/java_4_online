package ua.com.alevel.controller.sunglasses;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.cart.SunglassesCartDto;
import ua.com.alevel.facade.sunglasses.BrandFacade;
import ua.com.alevel.facade.sunglasses.SunglassesFacade;

@Controller
@RequestMapping("/sunglasses")
public class SunglassesController {

    private final SunglassesFacade sunglassesFacade;
    private final BrandFacade brandFacade;

    public SunglassesController(SunglassesFacade sunglassesFacade, BrandFacade brandFacade) {
        this.sunglassesFacade = sunglassesFacade;
        this.brandFacade = brandFacade;
    }

    @GetMapping
    public String plp(Model model, WebRequest webRequest) {
        model.addAttribute("sunglassesList", sunglassesFacade.findAll(webRequest));
        return "pages/open/sunglasses_plp";
    }

    @GetMapping("/{id}")
    public String pdp(Model model, @PathVariable Long id) {
        model.addAttribute("sunglasses", sunglassesFacade.findById(id));
        model.addAttribute("sunglassesCartDto", new SunglassesCartDto());
        return "pages/open/sunglasses_pdp";
    }

    @PostMapping("/{id}")
    public String pdp(@ModelAttribute("sunglassesCartDto") SunglassesCartDto sunglassesCartDto) {
        return "redirect:/cart/add/{id}";
    }

    @GetMapping("/{id}/add-to-cart")
    public String addToCartFromPdp(@PathVariable Long id) {
        return "pages/personal/add_success";
    }
}
