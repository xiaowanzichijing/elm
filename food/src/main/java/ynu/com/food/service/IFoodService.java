package ynu.com.food.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.com.common.entity.Food;

import java.util.List;

public interface IFoodService extends IService<Food> {
    public List<Food> listFoodByBusinessId(Integer businessId);
    public Food getFoodById(Integer foodId);
}
