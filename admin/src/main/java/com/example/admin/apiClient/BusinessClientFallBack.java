package com.example.admin.apiClient;

import org.springframework.stereotype.Component;
import ynu.com.common.entity.Business;
@Component//把它做成一个bean,放入依赖容器里
public class BusinessClientFallBack implements BusinessClient{
    @Override
    public Business getBusinessById(Integer businessId) {
        System.err.println("fallback~~~");
        return null;
    }
}
