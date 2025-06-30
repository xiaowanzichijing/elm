package ynu.com.deliveryaddress.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ynu.com.common.entity.DeliveryAddress;
import ynu.com.deliveryaddress.mapper.IDeliveryAddressMapper;
import ynu.com.deliveryaddress.service.IDeliveryAddressService;

import java.util.List;

@Service
public class IDeliveryAddressServiceImpl extends ServiceImpl<IDeliveryAddressMapper, DeliveryAddress> implements IDeliveryAddressService {
    private final IDeliveryAddressMapper iDeliveryAddressMapper;

    public IDeliveryAddressServiceImpl(IDeliveryAddressMapper iDeliveryAddressMapper) {
        this.iDeliveryAddressMapper = iDeliveryAddressMapper;
    }
    @Override
    public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) {
        List<DeliveryAddress> deliveryAddressList = iDeliveryAddressMapper.selectList(Wrappers.<DeliveryAddress>lambdaQuery().eq(DeliveryAddress::getUserId, userId));
        return deliveryAddressList;
    }
    @Override
    public DeliveryAddress getDeliveryAddressById(Integer daId) {
        DeliveryAddress deliveryAddress = iDeliveryAddressMapper.selectOne(Wrappers.<DeliveryAddress>lambdaQuery().eq(DeliveryAddress::getDaId,daId));

        return deliveryAddress;
    }
    @Override
    public int saveDeliveryAddress(DeliveryAddress deliveryAddress) {
        try {
            save(deliveryAddress);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    @Override
    public int updateDeliveryAddress(DeliveryAddress deliveryAddress) {
        // 根据送货地址编号更新送货地址信息
        UpdateWrapper<DeliveryAddress> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("daId", deliveryAddress.getDaId())
                .set("contactName", deliveryAddress.getContactName())
                .set("contactSex", deliveryAddress.getContactSex())
                .set("contactTel", deliveryAddress.getContactTel())
                .set("address", deliveryAddress.getAddress())
                .set("userId", deliveryAddress.getUserId());

        return iDeliveryAddressMapper.update(null, updateWrapper);
    }


    @Override
    public int removeDeliveryAddress(Integer daId) {
        return iDeliveryAddressMapper.deleteById(daId);
    }
}
