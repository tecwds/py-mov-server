package top.wpaint.pymov.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wpaint.pymov.model.entity.User;
import top.wpaint.pymov.service.UserService;
import top.wpaint.pymov.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author tecwds
* @description 针对表【pv_user】的数据库操作Service实现
* @createDate 2025-05-13 17:20:37
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




