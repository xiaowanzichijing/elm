package ynu.com.deliveryaddress.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.com.common.entity.DeliveryAddress;

import java.util.List;

public interface IDeliveryAddressService extends IService<DeliveryAddress> {
    List<DeliveryAddress> listDeliveryAddressByUserId(String userId);
    DeliveryAddress getDeliveryAddressById(Integer daId);
    int saveDeliveryAddress(DeliveryAddress deliveryAddress);
    int updateDeliveryAddress(DeliveryAddress deliveryAddress);
    int removeDeliveryAddress(Integer daId);
}
