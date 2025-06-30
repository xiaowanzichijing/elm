package ynu.com.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ynu.com.common.entity.User;
import ynu.com.user.mapper.IUserMapper;
import ynu.com.user.service.IUserService;

import java.util.HashMap;
import java.util.Map;

@Service
public class IUserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService {
    private final IUserMapper iUserMapper;

    public IUserServiceImpl(IUserMapper iUserMapper) {
        this.iUserMapper = iUserMapper;
    }

    @Override
    public User getUserByIdByPass(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserId());
        map.put("password", user.getPassword());

        return iUserMapper.selectOne(new QueryWrapper<User>().allEq(map));
    }
    @Override
    public int getUserById(String userId) {
        return iUserMapper.countUserById(userId);

    }
    @Override
    public int saveUser(User user) {
        try {
            save(user);
            return 1; // 返回1表示保存成功
        } catch (Exception e) {
            // 在保存失败时进行错误处理
            e.printStackTrace(); // 这里可以根据实际情况记录日志或执行其他操作
            return -1; // 返回-1表示保存失败
        }
    }
}