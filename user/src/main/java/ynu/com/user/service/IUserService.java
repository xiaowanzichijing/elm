package ynu.com.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.com.common.entity.User;

public interface IUserService extends IService<User> {
    public User getUserByIdByPass(User user);
    public int getUserById(String userId);
    public int saveUser(User user);
}
