package top.wpaint.pymov.service.impl;

import cn.hutool.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.wpaint.pymov.common.exception.PvException;
import top.wpaint.pymov.config.AppConfig;
import top.wpaint.pymov.domain.User;
import top.wpaint.pymov.domain.dto.LoginDto;
import top.wpaint.pymov.domain.vo.LoginVo;
import top.wpaint.pymov.mapper.UserMapper;
import top.wpaint.pymov.service.IUserService;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserMapper userMapper;
    private final AppConfig appConfig;

    @Override
    public LoginVo doLogin(LoginDto loginDto) throws PvException {
        User u = userMapper.selectByUsername(loginDto.getUsername());

        if (null == u) {
            throw new PvException("用户名或者密码错误");
        }

        if (!u.getPassword().equals(loginDto.getPassword())) {
            throw new PvException("用户名或者密码错误");
        }

        log.info("窃取了 {} 的基本数据!", u.getUsername());

        LoginVo vo = new LoginVo();
        String token = JWTUtil.createToken(
                Map.of("username", loginDto.getUsername()),
                appConfig.getTokenSecretKey().getBytes());
        vo.setToken(token);
        return vo;
    }
}
