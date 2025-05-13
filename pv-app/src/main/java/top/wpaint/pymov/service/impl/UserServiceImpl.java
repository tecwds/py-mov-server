package top.wpaint.pymov.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.stp.parameter.SaLoginParameter;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import top.wpaint.pymov.common.RCode;
import top.wpaint.pymov.mapper.UserMapper;
import top.wpaint.pymov.model.entity.User;
import top.wpaint.pymov.model.vo.LoginVo;
import top.wpaint.pymov.model.vo.UserVo;
import top.wpaint.pymov.service.UserService;
import top.wpaint.pymov.utils.PasswdUtil;
import top.wpaint.pymov.utils.ThrowUtils;

/**
 * @author tecwds
 * @description 针对表【pv_user】的数据库操作Service实现
 * @createDate 2025-05-13 17:51:07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public String registry(String account, String password) {
        ThrowUtils.throwIf(getUserByAccount(account) != null, RCode.OPERATION_ERROR, "用户已存在");

        User user = new User();
        user.setUserId(MD5.create().digestHex16(account));
        user.setUserAccount(account);
        user.setUserPassword(PasswdUtil.encryptPasswd(password));

        save(user);

        return user.getUserId();
    }

    @Override
    public LoginVo login(String account, String password) {
        User u = getUserByAccount(account);

        // 1. 判断密码是否正确
        ThrowUtils.throwIf(u == null || !u.getUserPassword().equals(PasswdUtil.encryptPasswd(password)),
                RCode.PARAMS_ERROR,
                "账号或密码错误");

        // 2. 登录并返回用户登录信息

        // 登录并保存用户信息
        StpUtil.login(u.getUserId());
        StpUtil.createLoginSession(u.getUserId(), new SaLoginParameter().setExtra(u.getUserId(), u));

        LoginVo vo = new LoginVo();
        vo.setUser(convertToVo(u));
        vo.setToken(StpUtil.getTokenValueByLoginId(u.getUserId()));

        return vo;
    }

    @Override
    public User getUserByAccount(String account) {
        return getOne(new LambdaQueryWrapper<User>().eq(User::getUserAccount, account));
    }

    @Override
    public UserVo convertToVo(User user) {
        UserVo vo = new UserVo();
        BeanUtils.copyProperties(user, vo);
        return vo;
    }


}




