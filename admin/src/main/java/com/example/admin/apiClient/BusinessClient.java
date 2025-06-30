package com.example.admin.apiClient;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ynu.com.common.entity.Business;

@FeignClient(name="business",fallback = BusinessClientFallBack.class)
@LoadBalancerClient(name = "business")
public interface BusinessClient {

    @PostMapping("/getBusinessById")
    Business getBusinessById(@RequestParam("businessId")Integer businessId);

}