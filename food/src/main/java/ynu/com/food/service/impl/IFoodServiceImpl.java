package ynu.com.food.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ynu.com.common.entity.Food;
import ynu.com.food.mapper.IFoodMapper;
import ynu.com.food.service.IFoodService;

import java.util.List;

@Service
public class IFoodServiceImpl extends ServiceImpl<IFoodMapper, Food> implements IFoodService {
    private final IFoodMapper iFoodMapper;

    public IFoodServiceImpl(IFoodMapper iFoodMapper) {
        this.iFoodMapper = iFoodMapper;
    }

    @Override
    public List<Food> listFoodByBusinessId(Integer businessId) {
        List<Food> foodList = iFoodMapper.selectList(Wrappers.<Food>lambdaQuery().eq(Food::getBusinessId, businessId));
        return foodList;
    }

    @Override
    public Food getFoodById(Integer foodId) {
        Food food = iFoodMapper.selectById(foodId);
        return food;
    }
}

