package top.wpaint.pymov.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wpaint.pymov.common.R;

@RestController
public class BaseController {

    /**
     * 健康检查
     *
     * @return "UP"
     */
    @GetMapping("healthy")
    public R<String> healthy() {
        return R.ok("UP");
    }
}
