package ynu.com.orders.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynu.com.common.entity.Orders;
import ynu.com.orders.apiClient.BusinessClient;
import ynu.com.orders.service.IOrdersService;
import ynu.com.orders.service.OrderDetailetService;

import java.util.List;

@RestController
// @RequestMapping("/orders")
@EnableFeignClients
public class OrdersController {
    private final IOrdersService iOrdersService;
    private final OrderDetailetService orderDetailetService;
    private BusinessClient businessClient;

    public OrdersController(@Qualifier("ynu.com.orders.apiClient.BusinessClient") BusinessClient businessClient,
            IOrdersService iOrdersService, OrderDetailetService orderDetailetService) {
        this.iOrdersService = iOrdersService;
        this.orderDetailetService = orderDetailetService;
        this.businessClient = businessClient;
    }

    @RequestMapping("/createOrders")
    public int createOrders(Orders orders) throws Exception {
        return iOrdersService.createOrders(orders);
    }

    @RequestMapping("/getOrdersById")
    public Orders getOrdersById(Orders orders) throws Exception {
        return iOrdersService.getOrdersById(orders.getOrderId());
    }

    @RequestMapping("/listOrdersByUserId")
    public List<Orders> listOrdersByUserId(Orders orders) throws Exception {
        return iOrdersService.listOrdersByUserId(orders.getUserId());
    }

    @RequestMapping("/updateOrdersById")
    public void updateOrdersById(Integer orderId) throws Exception {
        iOrdersService.updateOrdersById(orderId);
    }
}
