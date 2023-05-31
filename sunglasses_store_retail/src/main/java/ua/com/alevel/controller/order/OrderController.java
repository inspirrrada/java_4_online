package ua.com.alevel.controller.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.cart.CartFormDto;
import ua.com.alevel.dto.order.OrderDetailsDto;
import ua.com.alevel.dto.order.OrderSummaryDto;
import ua.com.alevel.facade.cart.CartFacade;
import ua.com.alevel.facade.order.OrderFacade;
import ua.com.alevel.facade.user.PersonalFacade;
import ua.com.alevel.persistence.entity.order.Order;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.repository.cart.CartItemRepository;
import ua.com.alevel.persistence.repository.cart.CartRepository;
import ua.com.alevel.persistence.repository.order.OrderItemRepository;
import ua.com.alevel.persistence.repository.order.OrderRepository;
import ua.com.alevel.util.SecurityUtil;

@Controller
@RequestMapping("/order")
@SessionAttributes({"orderDetails"})
public class OrderController {

    private final OrderFacade orderFacade;
    private final CartFacade cartFacade;
    private final PersonalFacade personalFacade;

    public OrderController(OrderFacade orderFacade, CartFacade cartFacade, PersonalFacade personalFacade) {
        this.orderFacade = orderFacade;
        this.cartFacade = cartFacade;
        this.personalFacade = personalFacade;
    }

    @GetMapping("/details")
    public String showOrderDetails(Model model) {
        Personal currentUser = personalFacade.findByEmail(SecurityUtil.getUsername());
        OrderDetailsDto orderDetailsDto = orderFacade.showOrderDetails(currentUser);
        model.addAttribute("orderDetails", orderDetailsDto);
        return "pages/personal/details";
    }

    @PostMapping("/preview")
    public String showOrderItems(Model model, @ModelAttribute("orderDetails") OrderDetailsDto orderDetailsDto) {
        Personal currentUser = personalFacade.findByEmail(SecurityUtil.getUsername());
        CartFormDto cartFormDto = cartFacade.findAllByCart(currentUser.getId());
        model.addAttribute("cartFormDto", cartFormDto);
        model.addAttribute("sunglassesCartDtoList", cartFormDto.getCartFormList());
        return "pages/personal/preview";
    }

    @PostMapping("/success")
    public String createNewOrder(@ModelAttribute("orderDetails") OrderDetailsDto orderDetailsDto) {
        Personal currentUser = personalFacade.findByEmail(SecurityUtil.getUsername());
        orderFacade.createNewOrder(orderDetailsDto, currentUser);
        return "pages/personal/order_new_success";
    }

    @GetMapping("/info/{id}")
    public String showOrderInfo(@PathVariable Long id, Model model) {
        Order order = orderFacade.findById(id);
        OrderSummaryDto orderSummaryDto = orderFacade.findAllByOrder(id);
        model.addAttribute("orderSummaryDto", orderSummaryDto);
        model.addAttribute("sunglassesOrderList", orderSummaryDto.getSunglassesOrderDtoList());
        OrderDetailsDto orderDetailsDto = orderFacade.showOrderDetails(order);
        model.addAttribute("orderDetailsSaved", orderDetailsDto);
        return "pages/personal/info";
    }
}
