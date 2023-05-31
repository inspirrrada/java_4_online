package ua.com.alevel.facade.cart.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.cart.CartFormDto;
import ua.com.alevel.dto.cart.SunglassesCartDto;
import ua.com.alevel.facade.cart.CartFacade;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.cart.CartItem;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.service.cart.CartService;
import ua.com.alevel.service.sunglasses.SunglassesService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Service
public class CartFacadeImpl implements CartFacade {

    private final CartService cartService;
    private final SunglassesService sunglassesService;

    public CartFacadeImpl(CartService cartService, SunglassesService sunglassesService) {
        this.cartService = cartService;
        this.sunglassesService = sunglassesService;
    }

    @Override
    public Cart findById(Long id) {
        return cartService.findById(id);
    }

    @Override
    public Cart findByUser(User user) {
        return cartService.findByUser(user);
    }

    @Override
    public CartFormDto findAllByCart(Long cartId) {
        Collection<CartItem> cartItems = cartService.findAllByCart(cartId);
        return new CartFormDto(cartItems
                .stream()
                .map(SunglassesCartDto::new)
                .toList());
    }

    @Override
    public void updateCart(CartFormDto cartFormDto, Long cartId) {
        Collection<SunglassesCartDto> cartFormList = cartFormDto.getCartFormList();
        Collection<CartItem> cartItems = new ArrayList<>();
                cartFormList.stream().forEach(v -> {
                    CartItem cartItem = convertSunglassesCartDtoToCartItem(v, cartId);
                    cartItems.add(cartItem);
                });
             cartService.updateCart(cartItems);
    }

    @Override
    public void addToCart(Long id, WebRequest webRequest, Cart cart) {
        Integer qtyForCart = 1;
        Map<String, String[]> map = webRequest.getParameterMap();
        if (MapUtils.isNotEmpty(map)) {
            String[] qtyArr = map.get("qty");
            if (qtyArr != null) {
                String qtyS = qtyArr[0];
                qtyForCart = Integer.parseInt(qtyS);
            }
        }
        cartService.addToCart(id, cart, qtyForCart);
    }

    private CartItem convertSunglassesCartDtoToCartItem(SunglassesCartDto sunglassesCartDto, Long cartId) {
        CartItem cartItem = new CartItem();
        cartItem.setId(sunglassesCartDto.getCartItemId());
        cartItem.setCart(findById(cartId));
        cartItem.setSunglasses(sunglassesService.findById(sunglassesCartDto.getId()).get());
        cartItem.setQuantity(sunglassesCartDto.getQty());
        if (sunglassesCartDto.isShouldBeRemoved()) {
            cartItem.setActive(false);
        } else {
            cartItem.setActive(true);
        }
        return cartItem;
    }
}
