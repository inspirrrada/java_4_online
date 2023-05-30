package ua.com.alevel.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.dto.order.OrderStatusDto;
import ua.com.alevel.facade.order.OrderFacade;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Controller
@RequestMapping(path = "/admin")
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
            List<OrderStatusDto> orderStatusDtoList = orderFacade.getOrdersInfoForAdmin();
            model.addAttribute("orderStatusDtoList", orderStatusDtoList);
        return "pages/admin/orders_table";
    }
}
