package ynu.com.cart.apiClient;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ynu.com.common.entity.Food;

@FeignClient(name = "food")
@LoadBalancerClient(name = "food")
public interface FoodClient {

    @GetMapping("/getFoodById")
    Food getFoodById(@RequestParam("foodId") Integer foodId);
}
