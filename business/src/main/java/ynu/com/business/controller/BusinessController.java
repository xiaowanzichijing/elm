package ynu.com.business.controller;

import org.springframework.web.bind.annotation.*;
import ynu.com.business.service.IBusinessService;
import ynu.com.common.entity.Business;

import java.util.List;

@RestController
//@RequestMapping("/business")
public class BusinessController {
    private final IBusinessService iBusinessService;

    public BusinessController(IBusinessService iBusinessService) {
        this.iBusinessService = iBusinessService;
    }

    @PostMapping("/listBusinessByOrderTypeId")
    public List<Business> listBusinessByOrderTypeId(Business business)throws Exception{
        return iBusinessService.listBusinessByOrderTypeId(business.getOrderTypeId());
    }

    @PostMapping("/getBusinessById")
    public Business getBusinessById(Integer businessId) throws Exception{
        return iBusinessService.getBusinessById(businessId);
    }
}
