package com.example.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ynu.com.common.entity.Business;
import ynu.com.common.entity.Food;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<Business> getAllBusiness(Integer pageSize, Integer offset, String keywords);
    Integer getBusinessTotalCount(String keywords);
    Integer getFoodTotalCount(Integer businessId);

    Integer insertBusiness(Business business);
    Integer insertFood(Food food);
    Integer deleteBusiness(Business business);
    Integer deleteFood(Food food);

    @Select("select * from business where businessId=#{businessId}")
    Business getBusinessById(Integer businessId);

    Integer updateBusinessInfo(Business business);
    Integer updateFoodInfo(Food food);


    List<Food> getFoodListById(Integer pageSize, Integer offset, Integer businessId);
}



