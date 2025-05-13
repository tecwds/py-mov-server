package top.wpaint.pymov.controller.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wpaint.pymov.common.R;

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
     */
    @PostMapping("register")
    public R<Void> register() {
        return R.ok();
    }

}
