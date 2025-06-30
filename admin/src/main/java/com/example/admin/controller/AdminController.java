package com.example.admin.controller;


import com.example.admin.apiClient.BusinessClient;
import com.example.admin.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynu.com.common.entity.Business;
import ynu.com.common.entity.Food;
import ynu.com.common.entity.PaginatedResult;

@RestController
//@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private BusinessClient businessClient;
    AdminController(BusinessClient businessClient, AdminService adminService){
        this.businessClient=businessClient;
        this.adminService = adminService;
    }
    @GetMapping("/getAllBusiness")
    public PaginatedResult<Business> getAllBusiness(@RequestParam Integer pageSize,
                                                    @RequestParam Integer pageNum,
                                                    @RequestParam(required = false,defaultValue = "%") String keywords){
        return  adminService.getAllBusiness(pageSize,pageNum,keywords);
    }
        @GetMapping("/getBusinessById")
    public ResponseEntity<?> getBusinessById(@RequestParam Integer businessId){

//        Business business=adminService.getBusinessById(businessId);
        Business business=businessClient.getBusinessById(businessId);
        if (business==null){
            return ResponseEntity.badRequest().body(new String("不存在该商户"));
        }
        return ResponseEntity.ok(business);
    }
    @PostMapping("/createBusiness")
    public ResponseEntity<?> createBusiness(@RequestBody Business business){
        System.out.println(business);
        Integer res= adminService.createBusiness(business);
        if(res==0){
            return ResponseEntity.badRequest().body(new String("创建失败"));
        }
        return ResponseEntity.ok(res);
    }
    @DeleteMapping("/deleteBusiness")
    public ResponseEntity<?> deleteBusiness(@RequestBody Business business){
        System.out.println(business);
        Integer res= adminService.deleteBusiness(business);
        if(res==0){
            return ResponseEntity.badRequest().body(new String("删除失败"));
        }
        return ResponseEntity.ok(res);
    }
    @PutMapping("/updateBusinessInfo")
    public ResponseEntity<?> updateBusinessInfo(@RequestBody Business business){
        System.out.println(business);
        Integer res= adminService.updateBusinessInfo(business);
        if(res==0){
            return ResponseEntity.badRequest().body(new String("更新失败"));
        }
        return ResponseEntity.ok(res);
    }
    @GetMapping("/getFoodListById")
    public PaginatedResult<Food> getFoodListById(@RequestParam Integer pageSize,
                                                 @RequestParam Integer pageNum,
                                                 @RequestParam Integer businessId){
        return adminService.getFoodListById(pageSize,pageNum,businessId);
    }
    @PostMapping("/createFood")
    public ResponseEntity<?> createFood(@RequestBody Food food){
        System.out.println(food);
        Integer res= adminService.createFood(food);
        if(res==0){
            return ResponseEntity.badRequest().body(new String("创建失败"));
        }
        return ResponseEntity.ok(res);
    }
    @DeleteMapping("/deleteFood")
    public ResponseEntity<?> deleteFood(@RequestBody Food food){
        System.out.println(food);
        Integer res= adminService.deleteFood(food);
        if(res==0){
            return ResponseEntity.badRequest().body(new String("删除失败"));
        }
        return ResponseEntity.ok(res);
    }
    @PutMapping("/updateFoodInfo")
    public ResponseEntity<?> updateFoodInfo(@RequestBody Food food){
        System.out.println(food);
        Integer res= adminService.updateFoodInfo(food);
        if(res==0){
            return ResponseEntity.badRequest().body(new String("更新失败"));
        }
        return ResponseEntity.ok(res);
    }
}
