package ynu.com.deliveryaddress.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynu.com.common.entity.DeliveryAddress;
import ynu.com.deliveryaddress.service.IDeliveryAddressService;

import java.util.List;

@RestController
//@RequestMapping("/delivery")
public class DeliveryAddressController {
    private final IDeliveryAddressService iDeliveryAddressService;
    public DeliveryAddressController(IDeliveryAddressService iDeliveryAddressService) {
        this.iDeliveryAddressService = iDeliveryAddressService;
    }
    @RequestMapping("/listDeliveryAddressByUserId")
    public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) throws Exception{
        return iDeliveryAddressService.listDeliveryAddressByUserId(userId);
    }
    @RequestMapping("/removeDeliveryAddress")
    public int removeDeliveryAddress(Integer daId) throws Exception{
        return iDeliveryAddressService.removeDeliveryAddress(daId);
    }
    @RequestMapping("/saveDeliveryAddress")
    public int saveDeliveryAddress(DeliveryAddress deliveryAddress)throws Exception{
        return iDeliveryAddressService.saveDeliveryAddress(deliveryAddress);
    }
    @RequestMapping("/getDeliveryAddressById")
    public DeliveryAddress getDeliveryAddressById(Integer daId)throws Exception{
        return iDeliveryAddressService.getDeliveryAddressById(daId);
    }
    @RequestMapping("/updateDeliveryAddress")
    public int updateDeliveryAddress(DeliveryAddress deliveryAddress)throws Exception{
        return iDeliveryAddressService.updateDeliveryAddress(deliveryAddress);
    }
}
