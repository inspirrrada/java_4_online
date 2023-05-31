package ua.com.alevel.controller.user;

import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.cart.CartFormDto;
import ua.com.alevel.dto.order.OrderStatusDto;
import ua.com.alevel.dto.order.StatusFormDto;
import ua.com.alevel.facade.order.OrderFacade;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping(path = "/admin")
@SessionAttributes({"statusFormDto"})
public class AdminController {

    private final OrderFacade orderFacade;

    public AdminController(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @GetMapping("/home")
    public String home() {
        return "pages/admin/home";
    }

    @GetMapping("/orders")
    public String showClientsOrders(Model model) {

//            Collection<OrderStatusDto> orderStatusDtoList = orderFacade.getOrdersInfoForAdmin();
//            model.addAttribute("orderStatusDtoList", orderStatusDtoList);
//            Collection<StatusFormDto> statusFormDtoList = new ArrayList<>();
//            model.addAttribute("statusForm", new StatusFormDto());
//            model.addAttribute("statusFormList", statusFormDtoList);
            StatusFormDto statusFormDto = new StatusFormDto(orderFacade.findAllOrderStatusDto());
        model.addAttribute("statusFormDto", statusFormDto);
        model.addAttribute("statusFormDtoList", statusFormDto.getOrderStatusDtoList());
        return "pages/admin/orders_table";
    }

    @PostMapping("/orders")
    public String changeOrderStatus(@ModelAttribute("statusFormDto") StatusFormDto statusFormDto){
        System.out.println("statusForm: " + statusFormDto);
        orderFacade.updateOrdersStatuses(statusFormDto);
//        System.out.println("orderStatusDtoList: " + orderStatusDtoList);
//        model.addAttribute("statusForm", new StatusForm());
        return "redirect:/admin/orders";
    }
}
