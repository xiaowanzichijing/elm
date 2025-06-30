package com.example.admin.service;


import ynu.com.common.entity.Business;
import ynu.com.common.entity.Food;
import ynu.com.common.entity.PaginatedResult;

public interface AdminService {

    PaginatedResult<Business> getAllBusiness(Integer pageSize, Integer pageNum, String keywords);
    Business getBusinessById(Integer businessId);

    Integer createBusiness(Business business);
    Integer createFood(Food food);
    Integer deleteBusiness(Business business);
    Integer deleteFood(Food food);
    Integer updateBusinessInfo(Business business);
    Integer updateFoodInfo(Food food);
    PaginatedResult<Food> getFoodListById(Integer pageSize, Integer pageNum,Integer businessId);
}
