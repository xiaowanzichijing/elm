package ynu.com.orders.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.com.common.entity.OrderDetailet;


import java.util.List;

public interface OrderDetailetService extends IService<OrderDetailet> {
    public void saveOrderDetailetBatch(OrderDetailet orderDetailet);
    public List<OrderDetailet> listOrderDetailetByOrderId(Integer orderId);
}

