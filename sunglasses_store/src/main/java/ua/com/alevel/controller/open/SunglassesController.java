package ua.com.alevel.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.sunglasses.BrandFacade;
import ua.com.alevel.facade.sunglasses.SunglassesFacade;
import ua.com.alevel.persistence.entity.brand.Brand;
import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;

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
        return "pages/open/sunglasses_pdp";
    }

    @GetMapping("/{id}/add-to-cart")
    public String addToCartFromPdp(@PathVariable Long id) {
        System.out.println("id: " + id);

        return "pages/personal/add_to_cart_successful";
    }

//    @GetMapping("/brand")
//    public String showByBrand(@RequestParam(value = "brand", required = false) String brandName) {
//        Brand brand = brandFacade.findByBrandName(brandName);
//        System.out.println("brandName: " + brandName);
//        System.out.println("brand: " + brand.toString());
//        return "";
//    }
}
