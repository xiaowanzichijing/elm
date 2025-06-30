package ynu.com.orders.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ynu.com.common.entity.Food;
import ynu.com.common.entity.OrderDetailet;
import ynu.com.orders.apiClient.FoodClient;
import ynu.com.orders.mapper.OrderDetailetMapper;
import ynu.com.orders.service.OrderDetailetService;

import java.util.List;

@Service
public class IOrderDetailetServiceImpl extends ServiceImpl<OrderDetailetMapper,OrderDetailet> implements OrderDetailetService{
    @Autowired
    private OrderDetailetMapper iOrderDetailetMapper;
    @Autowired
    private FoodClient iFoodService;
    @Override
    public void saveOrderDetailetBatch(OrderDetailet orderDetailet) {

        save(orderDetailet);
    }

    @Override
    public List<OrderDetailet> listOrderDetailetByOrderId(Integer orderId) {
//        List<OrderDetailet> orderDetailets = iOrderDetailetMapper.selectList(Wrappers.<OrderDetailet>lambdaQuery().eq(OrderDetailet::getOrderId, orderId));
//        return orderDetailets;

        LambdaQueryWrapper<OrderDetailet> wrapper = Wrappers.lambdaQuery();
        wrapper.select(OrderDetailet::getOdId,OrderDetailet::getOrderId,OrderDetailet::getFoodId,OrderDetailet::getQuantity/* 添加需要选择的字段 */)
                .eq(OrderDetailet::getOrderId, orderId);



        List<OrderDetailet> orderDetailetList = iOrderDetailetMapper.selectList(wrapper);

        for (OrderDetailet ordersItem : orderDetailetList) {
            Food food=iFoodService.getFoodById(ordersItem.getFoodId());

            // 设置关联的 list 和 business
            ordersItem.setFood(food);

        }
        return orderDetailetList;
    }
}