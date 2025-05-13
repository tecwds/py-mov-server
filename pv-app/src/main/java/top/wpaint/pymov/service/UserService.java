package top.wpaint.pymov.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.wpaint.pymov.model.entity.User;
import top.wpaint.pymov.model.vo.LoginVo;
import top.wpaint.pymov.model.vo.UserVo;

/**
* @author tecwds
* @description 针对表【pv_user】的数据库操作Service
* @createDate 2025-05-13 17:51:07
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param account  账号
     * @param password 密码
     * @return 用户 ID
     */
    String registry(String account, String password);

    /**
     * 用户登录
     * @param account 账号
     * @param password 密码
     * @return 用户脱敏信息
     */
    LoginVo login(String account, String password);

    /**
     * 根据账号获取用户
     *
     * @param account 账号
     * @return 用户实体
     */
    User getUserByAccount(String account);

    /**
     * 用户实体转换为用户脱敏信息
     * @param user 用户实体
     * @return 用户脱敏信息
     */
    UserVo convertToVo(User user);
}
