package ynu.com.cart.controller;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynu.com.cart.service.ICartService;
import ynu.com.common.entity.Cart;

import java.util.List;

@RestController
//@RequestMapping("/cart")
@EnableFeignClients
public class CartController {

    private final ICartService iCartService;

    public CartController(ICartService iCartService) {
        this.iCartService = iCartService;
    }

    @RequestMapping("/listCart")
    public List<Cart> listCart(Integer businessId,Integer userId) throws Exception{
        return iCartService.listCart(businessId,userId);
    }

    @RequestMapping("/saveCart")
    public int saveCart(Cart cart) throws Exception{
        return iCartService.saveCart(cart);
    }

    @RequestMapping("/updateCart")
    public int updateCart(Cart cart) throws Exception{
        return iCartService.updateCart(cart);
    }

    @RequestMapping("/removeCart")
    public int removeCart(Integer businessId,Integer userId,Integer foodId) throws Exception{
        return iCartService.removeCart(businessId,userId,foodId);
    }
}
