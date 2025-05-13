package top.wpaint.pymov.controller.auth;

import org.springframework.web.bind.annotation.*;
import top.wpaint.pymov.common.R;
import top.wpaint.pymov.model.dto.RegistryDto;

@RestController
@RequestMapping("auth")
public class AuthController {

    /**
     * 用户登录
     */
    @PostMapping("login")
    public R<Long> login() {
        return R.ok();
    }

    @GetMapping("logout")
    public R<Void> logout() {
        return R.ok();
    }

    /**
     * 用户注册
     * @param registryDto 注册信息
     */
    @PostMapping("register")
    public R<Void> register(@RequestBody RegistryDto registryDto) {
        return R.ok();
    }

}
