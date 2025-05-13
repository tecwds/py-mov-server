package top.wpaint.pymov.controller.auth;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.*;
import top.wpaint.pymov.common.R;
import top.wpaint.pymov.common.RCode;
import top.wpaint.pymov.model.dto.LoginDto;
import top.wpaint.pymov.model.dto.RegistryDto;
import top.wpaint.pymov.model.vo.LoginVo;
import top.wpaint.pymov.service.UserService;
import top.wpaint.pymov.utils.ThrowUtils;
import top.wpaint.pymov.utils.ValidUtils;

import javax.annotation.Resource;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     */
    @PostMapping("login")
    public R<LoginVo> login(@RequestBody LoginDto loginDto) {
        ValidUtils.requireNotNull(loginDto);
        return R.ok(userService.login(loginDto.getUserAccount(), loginDto.getUserPassword()));
    }

    /**
     * 用户登出
     */
    @GetMapping("logout")
    public R<Void> logout() {
        StpUtil.logout();
        return R.ok();
    }

    /**
     * 用户注册
     *
     * @param registryDto 注册信息
     */
    @PostMapping("register")
    public R<String> register(@RequestBody RegistryDto registryDto) {
        ValidUtils.requireNotNull(registryDto);

        ThrowUtils.throwIf(!registryDto.getPassword().equals(registryDto.getRePassword()),
                RCode.PARAMS_ERROR,
                "两次密码不匹配");

        String userId = userService.registry(registryDto.getUserAccount(), registryDto.getPassword());

        return R.ok(userId);
    }
}
