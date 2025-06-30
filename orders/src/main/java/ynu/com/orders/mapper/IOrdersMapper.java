package ynu.com.orders.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ynu.com.common.entity.Orders;

@Mapper
public interface IOrdersMapper extends BaseMapper<Orders> {

}