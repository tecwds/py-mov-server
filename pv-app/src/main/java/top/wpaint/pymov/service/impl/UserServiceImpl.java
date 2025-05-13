package top.wpaint.pymov.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.wpaint.pymov.mapper.UserMapper;
import top.wpaint.pymov.model.entity.User;
import top.wpaint.pymov.service.UserService;

/**
* @author tecwds
* @description 针对表【pv_user】的数据库操作Service实现
* @createDate 2025-05-13 17:51:07
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




