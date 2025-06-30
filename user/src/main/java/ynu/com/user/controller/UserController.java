package ynu.com.user.controller;

import org.springframework.web.bind.annotation.*;
import ynu.com.common.entity.User;
import ynu.com.user.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public User login(@RequestBody User user) throws Exception {
        return iUserService.getUserByIdByPass(user);
    }

    @PostMapping(value = "/register", consumes = "application/json")
    public int register(@RequestBody User user) throws Exception {
        return iUserService.saveUser(user);
    }

    @RequestMapping(value ="/getUserByIdByPass", consumes = "application/json")
    public User getUserByIdByPass(User user) throws Exception {
        return iUserService.getUserByIdByPass(user);
    }

    @RequestMapping(value = "/getUserById", method = {RequestMethod.GET, RequestMethod.POST})
    public int getUserById(@RequestParam("userId") String userId) throws Exception {
        return iUserService.getUserById(userId);
    }

    @RequestMapping(value ="/saveUser", consumes = "application/json")
    public int saveUser(User user) throws Exception {
        return iUserService.saveUser(user);
    }
}
