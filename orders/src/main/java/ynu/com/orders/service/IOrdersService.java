package ynu.com.orders.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.com.common.entity.Business;
import ynu.com.common.entity.Orders;

import java.util.List;

public interface IOrdersService extends IService<Orders> {

    public int createOrders(Orders orders);
    public Orders getOrdersById(Integer orderId);
    public List<Orders> listOrdersByUserId(String userId);
    public void updateOrdersById(Integer orderId);
    Business getBusinessByIdTest(Integer BusinessId);
}
