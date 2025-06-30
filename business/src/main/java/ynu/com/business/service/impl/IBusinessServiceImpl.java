package ynu.com.business.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ynu.com.business.mapper.IBusinessMapper;
import ynu.com.business.service.IBusinessService;
import ynu.com.common.entity.Business;

import java.util.List;

@Service
public class IBusinessServiceImpl extends ServiceImpl<IBusinessMapper, Business>implements IBusinessService {
    private final IBusinessMapper iBusinessMapper;

    public IBusinessServiceImpl(IBusinessMapper iBusinessMapper) {
        this.iBusinessMapper = iBusinessMapper;
    }

    @Override
    public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) {
        List<Business> businessList = iBusinessMapper.selectList(Wrappers.<Business>lambdaQuery().eq(Business::getOrderTypeId, orderTypeId));
        return businessList;
    }

    @Override
    public Business getBusinessById(Integer businessId) {
        return iBusinessMapper.selectById(businessId);
    }
}
