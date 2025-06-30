package ynu.com.food.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynu.com.common.entity.Cart;
import ynu.com.common.entity.Food;
import ynu.com.food.service.IFoodService;

import java.util.List;

@RestController
//@RequestMapping("/food")
public class FoodController {
    private final IFoodService iFoodService;

    public FoodController(IFoodService iFoodService) {
        this.iFoodService = iFoodService;
    }

    @RequestMapping("/listFoodByBusinessId")
    public List<Food> listFoodByBusinessId(Food food) throws Exception{
        return iFoodService.listFoodByBusinessId(food.getBusinessId());
    }

    @RequestMapping("/getFoodById")
    public Food getFoodById(Cart cartItem)throws Exception{
        return iFoodService.getFoodById(cartItem.getFoodId());
    }
}
