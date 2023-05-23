package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.data.response.CartFormDto;
import ua.com.alevel.data.response.SunglassesCartDto;
import ua.com.alevel.facade.CartFacade;
import ua.com.alevel.facade.user.PersonalFacade;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.util.SecurityUtil;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final PersonalFacade personalFacade;
    private final CartFacade cartFacade;

    public OrderController(PersonalFacade personalFacade, CartFacade cartFacade) {
        this.personalFacade = personalFacade;
        this.cartFacade = cartFacade;
    }

    @GetMapping("/items")
    public String showOrderItems(Model model) {
        String email = SecurityUtil.getUsername();
        Personal personal = personalFacade.findByEmail(email);
        List<SunglassesCartDto> sunglassesCartDtoList = cartFacade.findAllByCart(personal.getId()).stream().toList();
        model.addAttribute("sunglassesCartDtoList", sunglassesCartDtoList);
        System.out.println("sunglassesCartDtoList from orderController: " + sunglassesCartDtoList);
        int totalQty = sunglassesCartDtoList.stream().mapToInt(SunglassesCartDto::getQty).sum();
        model.addAttribute("totalQty", totalQty);
        BigDecimal sum = BigDecimal.ZERO;
        for (SunglassesCartDto sunglassesCartDto : sunglassesCartDtoList) {
            sum = sum.add(sunglassesCartDto.getTotalPrice());
        }
        model.addAttribute("totalAmount", sum);
        return "pages/personal/items";
    }
}
