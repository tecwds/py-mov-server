package top.wpaint.pymov.service;

import top.wpaint.pymov.common.exception.PvException;
import top.wpaint.pymov.domain.dto.LoginDto;
import top.wpaint.pymov.domain.vo.LoginVo;

public interface IUserService {
    LoginVo doLogin(LoginDto loginDto) throws PvException;
}
