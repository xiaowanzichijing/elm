package com.example.admin.service.impl;


import com.example.admin.mapper.AdminMapper;
import com.example.admin.service.AdminService;
import org.springframework.stereotype.Service;
import ynu.com.common.entity.Business;
import ynu.com.common.entity.Food;
import ynu.com.common.entity.PaginatedResult;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;
    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }
    @Override
    public PaginatedResult<Business> getAllBusiness(Integer pageSize, Integer pageNum, String keywords) {
        Integer offset = (pageNum - 1) * pageSize;
        keywords="%"+keywords+"%";
        List<Business> businesses = adminMapper.getAllBusiness(pageSize, offset, keywords);

        Integer totalCount = adminMapper.getBusinessTotalCount(keywords);
        PaginatedResult<Business> result = new PaginatedResult<>();
        result.setList(businesses);
        result.setTotalCount(totalCount);

        return result;
    }
    @Override
    public Business getBusinessById(Integer businessId) {
        return adminMapper.getBusinessById(businessId);
    }
    @Override
    public Integer createBusiness(Business business) {
        return adminMapper.insertBusiness(business);
    }
    @Override
    public Integer createFood(Food food) {
        return adminMapper.insertFood(food);
    }
    @Override
    public Integer deleteBusiness(Business business) {
        return adminMapper.deleteBusiness(business);
    }
    @Override
    public Integer deleteFood(Food food) {
        return adminMapper.deleteFood(food);
    }
    @Override
    public Integer updateBusinessInfo(Business business) {
        return adminMapper.updateBusinessInfo(business);
    }
    @Override
    public PaginatedResult<Food> getFoodListById(Integer pageSize, Integer pageNum, Integer businessId) {
        Integer offset = (pageNum - 1) * pageSize;

        List<Food> foods = adminMapper.getFoodListById(pageSize, offset, businessId);

        Integer totalCount = adminMapper.getFoodTotalCount(businessId);
        PaginatedResult<Food> result = new PaginatedResult<>();
        result.setList(foods);
        result.setTotalCount(totalCount);

        return result;
    }
    @Override
    public Integer updateFoodInfo(Food food) {
        return adminMapper.updateFoodInfo(food);
    }
}
