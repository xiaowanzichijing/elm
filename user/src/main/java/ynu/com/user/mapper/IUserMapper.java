package ynu.com.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ynu.com.common.entity.User;

@Mapper
public interface IUserMapper extends BaseMapper<User> {
    int countUserById (String userId);
}
