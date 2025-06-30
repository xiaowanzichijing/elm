package ynu.com.orders.apiClient;

import org.springframework.stereotype.Component;
import ynu.com.common.entity.Business;

@Component
public class BusinessClientFallBack implements BusinessClient{
    @Override
    public Business getBusinessById(Integer businessId) {
        System.err.println("fallback~~~");
        return null;
    }
}
