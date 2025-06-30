package ynu.com.orders.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ynu.com.common.entity.Business;
import ynu.com.common.entity.Cart;
import ynu.com.common.entity.OrderDetailet;
import ynu.com.common.entity.Orders;
import ynu.com.orders.apiClient.BusinessClient;
import ynu.com.orders.apiClient.CartClient;
import ynu.com.orders.mapper.IOrdersMapper;
import ynu.com.orders.service.IOrdersService;
import ynu.com.orders.service.OrderDetailetService;
import ynu.com.orders.util.CommonUtil;

import java.util.List;

@Service
public class IOrdersServiceImpl extends ServiceImpl<IOrdersMapper, Orders> implements IOrdersService {
    @Autowired
    private IOrdersMapper iOrdersMapper;
    @Autowired
    private CartClient iCartClient;
    @Autowired
    private OrderDetailetService iOrderDetailetService;
    @Qualifier("ynu.com.orders.apiClient.BusinessClient")
    @Autowired
    private BusinessClient iBusinessService;
    @Override
    public int createOrders(Orders orders) {
        // 1、查询当前用户购物车中当前商家的所有食品
        Cart cart = new Cart();
        cart.setUserId(orders.getUserId());
        cart.setBusinessId(orders.getBusinessId());
        List<Cart> cartList = iCartClient.listCart(cart.getBusinessId(), Integer.valueOf(cart.getUserId()));
        // 2、创建订单（返回生成的订单编号）
        orders.setOrderDate(CommonUtil.getCurrentDate());
        save(orders);
        int orderId = orders.getOrderId();

        // 3、批量添加订单明细
        // List<OrderDetailet> list = new ArrayList<>();
        for (Cart c : cartList) {
            OrderDetailet od = new OrderDetailet();
            od.setOrderId(orderId);
            od.setFoodId(c.getFoodId());
            od.setQuantity(c.getQuantity());

            iOrderDetailetService.saveOrderDetailetBatch(od);
        }
        // 4、从购物车表中删除相关食品信息
        iCartClient.removeCart(cart.getBusinessId(), Integer.valueOf(cart.getUserId()), cart.getFoodId());

        return orderId;
    }
    @Override
    public Orders getOrdersById(Integer orderId) {
        LambdaQueryWrapper<Orders> wrapper = Wrappers.lambdaQuery();
        wrapper.select(Orders::getOrderId, Orders::getUserId, Orders::getBusinessId, Orders::getOrderDate,
                Orders::getOrderTotal, Orders::getDaId, Orders::getOrderState/* 添加需要选择的字段 */)
                .eq(Orders::getOrderId, orderId);

        Orders orders = iOrdersMapper.selectOne(wrapper);
        Business business = iBusinessService.getBusinessById(orders.getBusinessId());
        List<OrderDetailet> orderDetailetList = iOrderDetailetService.listOrderDetailetByOrderId(orders.getOrderId());
        // 设置关联的 list 和 business
        orders.setList(orderDetailetList);
        orders.setBusiness(business);
        return orders;

    }
    @Override
    public Business getBusinessByIdTest(Integer BusinessId) {
        return iBusinessService.getBusinessById(BusinessId);
    }
    @Override
    public List<Orders> listOrdersByUserId(String userId) {

        LambdaQueryWrapper<Orders> wrapper = Wrappers.lambdaQuery();
        wrapper.select(Orders::getOrderId, Orders::getUserId, Orders::getBusinessId, Orders::getOrderDate,
                Orders::getOrderTotal, Orders::getDaId, Orders::getOrderState/* 添加需要选择的字段 */)
                .eq(Orders::getUserId, userId);

        List<Orders> ordersList = iOrdersMapper.selectList(wrapper);

        for (Orders ordersItem : ordersList) {
            Business business = iBusinessService.getBusinessById(ordersItem.getBusinessId());
            List<OrderDetailet> orderDetailetList = iOrderDetailetService
                    .listOrderDetailetByOrderId(ordersItem.getOrderId());

            // 设置关联的 list 和 business
            ordersItem.setList(orderDetailetList);
            ordersItem.setBusiness(business);
        }
        return ordersList;
    }
    @Override
    public void updateOrdersById(Integer orderId) {

        Orders order = getOrdersById(orderId);
        order.setOrderState(1);
        // 更新订单状态
        LambdaUpdateWrapper<Orders> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(Orders::getOrderId, orderId)
                .set(Orders::getOrderState, order.getOrderState());
        iOrdersMapper.update(null, updateWrapper);
    }
}
