package ua.com.alevel.controller.cart;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.cart.CartFormDto;
import ua.com.alevel.dto.cart.SunglassesCartDto;
import ua.com.alevel.facade.cart.CartFacade;
import ua.com.alevel.facade.user.PersonalFacade;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.cart.CartItem;
import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.repository.cart.CartItemRepository;
import ua.com.alevel.persistence.repository.cart.CartRepository;
import ua.com.alevel.service.sunglasses.SunglassesService;
import ua.com.alevel.util.SecurityUtil;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

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
        List<SunglassesCartDto> sunglassesCartDtoList = cartFacade.findAllByCart(personal.getId()).stream().toList();
        CartFormDto cartFormDto = new CartFormDto(sunglassesCartDtoList);
        model.addAttribute("cartList", sunglassesCartDtoList);
        model.addAttribute("cartFormDto", cartFormDto);
        System.out.println("cartFormDto: " + cartFormDto);
        System.out.println("cartFormList: " + cartFormDto.getCartFormList());
        int totalQty = sunglassesCartDtoList.stream().mapToInt(SunglassesCartDto::getQty).sum();
        model.addAttribute("totalQty", totalQty);
        BigDecimal sum = BigDecimal.ZERO;
        for (SunglassesCartDto sunglassesCartDto : sunglassesCartDtoList) {
            sum = sum.add(sunglassesCartDto.getTotalPrice());
        }
        model.addAttribute("totalAmount", sum);
//        System.out.println("cartFormList[0].imageUrl: " + cartFormDto.getCartFormList().get(0).getImageUrl1());
//        String s = sunglassesCartDtoList.stream().toList().get(0).getImageUrl1();
        return "pages/personal/cart";
    }

    @PostMapping
    public String updateCart(@ModelAttribute("cartFormDto") CartFormDto cartFormDto) {
        String email = SecurityUtil.getUsername();
        Personal personal = personalFacade.findByEmail(email);
        System.out.println("cartFormDto: " + cartFormDto);
        cartFacade.updateCart(cartFormDto, personal.getId());
        return "redirect:/cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, WebRequest webRequest, @ModelAttribute("sunglassesCartDto") SunglassesCartDto sunglassesCartDto) {
        System.out.println("Model Attr sunglassesCartDto " + sunglassesCartDto);
        String email = SecurityUtil.getUsername();
        Personal personal = personalFacade.findByEmail(email);
        Cart cart = cartRepository.findById(personal.getId()).get();
        System.out.println("personal: " + personal);
//        Cart cart = cartFacade.findByUser(personal);
//        System.out.println("cart: " + cart);
        Sunglasses sunglassesCurrent = sunglassesService.findById(id).get();
        System.out.println("sunglassesCurrent: " + sunglassesCurrent);
        Collection<CartItem> cartItems = cartItemRepository.findAllByCart(cart);
        AtomicBoolean alreadyExistInCart = new AtomicBoolean(false);
        cartItems.forEach(v -> {
            if (v.getSunglasses().getId() == id) {
                alreadyExistInCart.set(true);
            }
        });
        Integer qtyForCart = 1;
        Map<String, String[]> map = webRequest.getParameterMap();
        if (MapUtils.isNotEmpty(map)) {
            String[] qtyArr = map.get("qty");
            if (qtyArr != null) {
                String qtyS = qtyArr[0];
                qtyForCart = Integer.parseInt(qtyS);
            }
        }
        CartItem cartItem = cartItemRepository.findByCartIdAndSunglasses(personal.getId(), sunglassesCurrent);
        CartItem cartItemNew;
        if (cartItem == null) {
            cartItemNew = new CartItem();
            cartItemNew.setSunglasses(sunglassesCurrent);
            cartItemNew.setQuantity(qtyForCart);
            cartItemNew.setCart(cart);
            cartItemRepository.save(cartItemNew);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + qtyForCart);
            cartItemRepository.save(cartItem);
        }



//        Set<CartItem> cartItems = cart.getCartItems();
//        cartItems.add(cartItemNew);
//        System.out.println("cart: " + cart);
//        cartRepository.save(cart);

//        model.addAttribute("sunglassesList", sunglassesFacade.findAll(webRequest));
        return "pages/personal/add_to_cart_successful";
//        return "pages/personal/cart";
    }

//    @GetMapping
//    public v
}
