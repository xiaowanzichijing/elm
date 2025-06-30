package ynu.com.food.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ynu.com.common.entity.Food;
@Mapper
public interface IFoodMapper extends BaseMapper<Food> {
}
