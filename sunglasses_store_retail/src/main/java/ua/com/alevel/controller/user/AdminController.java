package ua.com.alevel.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.order.StatusFormDto;
import ua.com.alevel.facade.order.OrderFacade;

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
        StatusFormDto statusFormDto = new StatusFormDto(orderFacade.findAllOrderStatusDto());
        model.addAttribute("statusFormDto", statusFormDto);
        model.addAttribute("statusFormDtoList", statusFormDto.getOrderStatusDtoList());
        return "pages/admin/orders_table";
    }

    @PostMapping("/orders")
    public String changeOrderStatus(@ModelAttribute("statusFormDto") StatusFormDto statusFormDto){
        orderFacade.updateOrdersStatuses(statusFormDto);
        return "redirect:/admin/orders";
    }
}
