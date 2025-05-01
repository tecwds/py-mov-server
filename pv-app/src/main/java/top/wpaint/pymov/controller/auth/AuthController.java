package top.wpaint.pymov.controller.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wpaint.pymov.common.R;
import top.wpaint.pymov.common.exception.PvException;
import top.wpaint.pymov.domain.dto.LoginDto;
import top.wpaint.pymov.domain.vo.LoginVo;
import top.wpaint.pymov.service.IUserService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final IUserService userService;

    @PostMapping("login")
    public R<LoginVo> login(@RequestBody LoginDto loginDto) throws PvException {
        return R.ok(userService.doLogin(loginDto));
    }
}
