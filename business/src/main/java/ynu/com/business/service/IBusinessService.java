package ynu.com.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.com.common.entity.Business;

import java.util.List;

public interface IBusinessService extends IService<Business> {
    List<Business> listBusinessByOrderTypeId(Integer orderTypeId);
    Business getBusinessById(Integer businessId);
}
