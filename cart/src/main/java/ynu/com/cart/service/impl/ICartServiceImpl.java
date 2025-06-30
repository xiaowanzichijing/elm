package ynu.com.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ynu.com.cart.apiClient.BusinessClient;
import ynu.com.cart.apiClient.FoodClient;
import ynu.com.cart.mapper.ICartMapper;
import ynu.com.cart.service.ICartService;
import ynu.com.common.entity.Business;
import ynu.com.common.entity.Cart;
import ynu.com.common.entity.Food;

import java.util.List;

@Service
public class ICartServiceImpl extends ServiceImpl<ICartMapper, Cart> implements ICartService {
    private final ICartMapper iCartMapper;
    private final BusinessClient iBusinessService;
    private final FoodClient iFoodService;
    public ICartServiceImpl(ICartMapper iCartMapper, BusinessClient iBusinessService, FoodClient iFoodService) {
        this.iCartMapper = iCartMapper;
        this.iBusinessService = iBusinessService;
        this.iFoodService = iFoodService;
    }
    @Override
    public List<Cart> listCart(Integer businessId,Integer userId) {
        LambdaQueryWrapper<Cart> wrapper = Wrappers.lambdaQuery();
        wrapper.select(Cart::getCartId, Cart::getFoodId,Cart::getBusinessId,Cart::getUserId,Cart::getQuantity /* 添加需要选择的字段 */)
                .eq(Cart::getUserId, userId);

        if (businessId != null) {
            wrapper.eq(Cart::getBusinessId, businessId);
        }

        List<Cart> cartList = iCartMapper.selectList(wrapper);
        // 获取 food 和 business 信息
        for (Cart cartItem : cartList) {
            Food food = iFoodService.getFoodById(cartItem.getFoodId());
            Business business = iBusinessService.getBusinessById(cartItem.getBusinessId());

            // 设置关联的 food 和 business
            cartItem.setFood(food);
            cartItem.setBusiness(business);
        }
        return cartList;
    }
    @Override
    public int saveCart(Cart cart) {
        try {
            save(cart);
            return 1; // 返回1表示保存成功
        } catch (Exception e) {
            // 在保存失败时进行错误处理
            e.printStackTrace(); // 这里可以根据实际情况记录日志或执行其他操作
            return -1; // 返回-1表示保存失败
        }
    }

    @Override
    public int updateCart(Cart cart) {
        try {
            UpdateWrapper<Cart> updateWrapper=new  UpdateWrapper<>();
            updateWrapper.set("quantity",cart.getQuantity()).eq("foodId",cart.getFoodId()).eq("businessId",cart.getBusinessId()).eq("userId",cart.getUserId());
            return iCartMapper.update(null, updateWrapper);
        } catch (Exception e) {
            e.printStackTrace(); // 这里可以根据实际情况记录日志或执行其他操作
            return -1; // 返回-1表示保存失败
        }
    }

    @Override
    public int removeCart(Integer businessId,Integer userId,Integer foodId ) {
        try {
            UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("userId", userId)
                    .eq("businessId", businessId);

            if (foodId != null) {
                updateWrapper.eq("foodId", foodId);
            }

            return iCartMapper.delete(updateWrapper);
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // 返回-1表示删除失败
        }
    }
}
