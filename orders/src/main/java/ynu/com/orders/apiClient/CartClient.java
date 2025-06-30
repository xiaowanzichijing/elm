package ynu.com.orders.apiClient;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ynu.com.common.entity.Cart;

import java.util.List;

@FeignClient(name="cart")
@LoadBalancerClient(name = "cart")
public interface CartClient {
    @RequestMapping("/listCart")
    List<Cart> listCart(@RequestParam("businessId")Integer businessId,@RequestParam("userId") Integer userId);

    @RequestMapping("/removeCart")
    int removeCart(@RequestParam("businessId")Integer businessId,@RequestParam("userId")Integer userId,@RequestParam("foodId")Integer foodId);
}
