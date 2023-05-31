package ua.com.alevel.controller.cart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.cart.CartFormDto;
import ua.com.alevel.facade.cart.CartFacade;
import ua.com.alevel.facade.user.PersonalFacade;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.util.SecurityUtil;

@Controller
@RequestMapping(path="/cart")
public class CartController {

    private final PersonalFacade personalFacade;
    private final CartFacade cartFacade;

    public CartController(PersonalFacade personalFacade, CartFacade cartFacade) {
        this.personalFacade = personalFacade;
        this.cartFacade = cartFacade;
    }

    @GetMapping
    public String openCart(Model model) {
        Personal currentUser = personalFacade.findByEmail(SecurityUtil.getUsername());
        CartFormDto cartFormDto = cartFacade.findAllByCart(currentUser.getId());
        model.addAttribute("cartFormDto", cartFormDto);
        model.addAttribute("cartList", cartFormDto.getCartFormList());
        return "pages/personal/cart";
    }

    @PostMapping
    public String updateCart(@ModelAttribute("cartFormDto") CartFormDto cartFormDto) {
        Personal currentUser = personalFacade.findByEmail(SecurityUtil.getUsername());
        cartFacade.updateCart(cartFormDto, currentUser.getId());
        return "redirect:/cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id,
                            WebRequest webRequest) {
        Personal currentUser = personalFacade.findByEmail(SecurityUtil.getUsername());
        Cart cart = cartFacade.findById(currentUser.getId());
        cartFacade.addToCart(id, webRequest, cart);
        return "pages/personal/add_success";
    }
}
