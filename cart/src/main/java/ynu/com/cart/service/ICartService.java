package ynu.com.cart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.com.common.entity.Cart;

import java.util.List;

public interface ICartService extends IService<Cart> {

    public List<Cart> listCart(Integer businessId,Integer userId);
    public int saveCart(Cart cart);
    public int updateCart(Cart cart);
    public int removeCart(Integer businessId,Integer userId,Integer foodId);
}
