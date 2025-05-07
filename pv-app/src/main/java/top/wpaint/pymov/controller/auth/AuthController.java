package top.wpaint.pymov.controller.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.wpaint.pymov.common.R;
import top.wpaint.pymov.common.exception.PvException;
import top.wpaint.pymov.domain.dto.LoginDto;
import top.wpaint.pymov.domain.dto.RegisterDto;
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

    @PostMapping("register")
    public R<Void> register(@RequestBody RegisterDto registerDto) throws PvException {
        userService.doRegister(registerDto);
        return R.ok();
    }

    @GetMapping("logout")
    public R<Void> logout() {
        return R.ok();
    }
}
