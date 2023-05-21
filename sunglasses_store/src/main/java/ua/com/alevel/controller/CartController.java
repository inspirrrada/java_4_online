package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.data.response.SunglassesCartDto;
import ua.com.alevel.facade.CartFacade;
import ua.com.alevel.facade.user.PersonalFacade;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.cart.CartItem;
import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.repository.CartItemRepository;
import ua.com.alevel.persistence.repository.CartRepository;
import ua.com.alevel.service.SunglassesService;
import ua.com.alevel.util.SecurityUtil;

import java.util.Collection;
import java.util.Set;

@Controller
@RequestMapping(path="/cart")
public class CartController {

    private final PersonalFacade personalFacade;
    private final CartFacade cartFacade;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final SunglassesService sunglassesService;

    public CartController(PersonalFacade personalFacade, CartFacade cartFacade, CartRepository cartRepository, CartItemRepository cartItemRepository, SunglassesService sunglassesService) {
        this.personalFacade = personalFacade;
        this.cartFacade = cartFacade;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.sunglassesService = sunglassesService;
    }

    @GetMapping
    public String openCart(Model model) {
        String email = SecurityUtil.getUsername();
        Personal personal = personalFacade.findByEmail(email);
        Collection<SunglassesCartDto> sunglassesCartDtoList = cartFacade.findAllByCart(personal.getId()).stream().toList();
        model.addAttribute("cartList", sunglassesCartDtoList);
        String s = sunglassesCartDtoList.stream().toList().get(0).getImageUrl1();
        return "pages/personal/cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id) {
//        System.out.println("id from cart: " + id);
        String email = SecurityUtil.getUsername();
        Personal personal = personalFacade.findByEmail(email);
        System.out.println("personal: " + personal);
//        Cart cart = cartFacade.findByUser(personal);
//        System.out.println("cart: " + cart);
        Sunglasses sunglassesCurrent = sunglassesService.findById(id).get();
        System.out.println("sunglassesCurrent: " + sunglassesCurrent);
        CartItem cartItemNew = new CartItem();
        cartItemNew.setSunglasses(sunglassesCurrent);
        cartItemNew.setQuantity(5);
        Cart cart = cartRepository.findById(personal.getId()).get();
        cartItemNew.setCart(cart);
//        Set<CartItem> cartItems = cart.getCartItems();
//        cartItems.add(cartItemNew);
        System.out.println("cartItemNew: " + cartItemNew);
//        System.out.println("cart: " + cart);
//        cartRepository.save(cart);
        cartItemRepository.save(cartItemNew);
//        model.addAttribute("sunglassesList", sunglassesFacade.findAll(webRequest));
        return "pages/personal/add_to_cart_successful";
//        return "pages/personal/cart";
    }

//    @GetMapping
//    public v
}
