package ua.com.alevel.controller.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.order.OrderDetailsDto;
import ua.com.alevel.dto.order.OrderFullInfoDto;
import ua.com.alevel.dto.cart.SunglassesCartDto;
import ua.com.alevel.facade.cart.CartFacade;
import ua.com.alevel.facade.order.OrderFacade;
import ua.com.alevel.facade.user.PersonalFacade;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.cart.CartItem;
import ua.com.alevel.persistence.entity.order.Order;
import ua.com.alevel.persistence.entity.order.OrderItem;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.repository.cart.CartItemRepository;
import ua.com.alevel.persistence.repository.cart.CartRepository;
import ua.com.alevel.persistence.repository.order.OrderItemRepository;
import ua.com.alevel.persistence.repository.order.OrderRepository;
import ua.com.alevel.persistence.type.order.OrderStatusType;
import ua.com.alevel.util.SecurityUtil;

import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/order")
@SessionAttributes({"orderDetails"})
public class OrderController {

    private final OrderFacade orderFacade;
    private final CartFacade cartFacade;
    private final PersonalFacade personalFacade;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderController(OrderFacade orderFacade, CartFacade cartFacade, PersonalFacade personalFacade, CartRepository cartRepository, CartItemRepository cartItemRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderFacade = orderFacade;
        this.cartFacade = cartFacade;
        this.personalFacade = personalFacade;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @GetMapping("/details")
    public String showOrderDetails(Model model) {
        String email = SecurityUtil.getUsername();
        Personal personal = personalFacade.findByEmail(email);

        OrderDetailsDto orderDetailsDto = new OrderDetailsDto(personal);
        model.addAttribute("orderDetails", orderDetailsDto);

        return "pages/personal/details";
    }

//    @PostMapping("/details")
//    public String updateOrderDetails(@ModelAttribute("orderDetails") OrderDetailsDto orderDetailsDto) {
//        System.out.println("orderDetailsDto in details page: " + orderDetailsDto);
//        return "pages/personal/details";
//    }

//    @PostMapping("/details")
//    public String saveOrderDetails(@ModelAttribute("orderDetails") OrderDetailsDto orderDetailsDto) {
//        System.out.println("orderDetailsDto in details page after return: " + orderDetailsDto);
//       return "pages/personal/details";
//    }


    @PostMapping("/preview")
    public String showOrderItems(Model model, @ModelAttribute("orderDetails") OrderDetailsDto orderDetailsDto) {
        System.out.println("orderDetailsDto in preview page: " + orderDetailsDto);
//        orderFacade.saveOrderDetails(OrderDetailsDto orderDetailsDto);
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
        return "pages/personal/preview";
    }

    @PostMapping("/success")
    public String createNewOrder(@ModelAttribute("orderDetails") OrderDetailsDto orderDetailsDto) {
        System.out.println("orderDetailsDto create new order: " + orderDetailsDto);
        String email = SecurityUtil.getUsername();
        Personal personal = personalFacade.findByEmail(email);
        Cart cart = cartRepository.findById(personal.getId()).get();
        Order order = new Order();
        order.setNumber("SB-000" + order.getId());
        order.setUser(personal);
        order.setContactFirstName(orderDetailsDto.getContactFirstName());
        order.setContactLastName(orderDetailsDto.getContactLastName());
        order.setContactPhoneNumber(orderDetailsDto.getContactPhoneNumber());
        order.setReservePhoneNumber(orderDetailsDto.getReservePhoneNumber());
        order.setDeliveryZip(orderDetailsDto.getDeliveryZip());
        order.setDeliveryRegion(orderDetailsDto.getDeliveryRegion());
        order.setDeliveryCity(orderDetailsDto.getDeliveryCity());
        order.setDeliveryStreet(orderDetailsDto.getDeliveryStreet());
        order.setDeliveryBuilding(orderDetailsDto.getDeliveryBuilding());
        order.setDeliveryApartment(orderDetailsDto.getDeliveryApartment());
        order.setOrderStatus(OrderStatusType.CREATED);
//        order.setOrderNotes(orderDetailsDto);
        order.setPaymentMethod(orderDetailsDto.getPaymentMethod());
        orderRepository.save(order);
        Collection<CartItem> cartItems = cartItemRepository.findAllByCart(cart);
        Set<OrderItem> orderItems = new HashSet<>();
        cartItems.stream().forEach(v -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setQuantity(v.getQuantity());
            orderItem.setSunglasses(v.getSunglasses());
            orderItem.setPrice(v.getSunglasses().getPrice());
            orderItems.add(orderItem);
            orderItemRepository.save(orderItem);
        });
        order.setOrderItems(orderItems);

        cartItemRepository.deleteAll(cartItems);

        return "pages/personal/order_successful";
    }

    @GetMapping("/info/{id}")
    public String showOrderInfo(@PathVariable Long id, Model model) {
        Order order = orderRepository.findById(id).get();
        model.addAttribute("orderInfo", new OrderFullInfoDto(order));
        String email = SecurityUtil.getUsername();
        Personal personal = personalFacade.findByEmail(email);
        List<OrderItem> sunglassesOrderedList = orderItemRepository.findAllByOrder(order); //convert to SunglassesOrderDto
        model.addAttribute("sunglassesOrderedList", sunglassesOrderedList);
        int totalQty = sunglassesOrderedList.stream().mapToInt(OrderItem::getQuantity).sum();
        model.addAttribute("totalQty", totalQty);
        BigDecimal sum = BigDecimal.ZERO;
        for (OrderItem orderItem : sunglassesOrderedList) {
            sum = sum.add(orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity())));
        }
        model.addAttribute("totalAmount", sum);
        return "pages/personal/info";
    }

}
