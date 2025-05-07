package top.wpaint.pymov.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wpaint.pymov.common.R;
import top.wpaint.pymov.domain.vo.OccupationVo;
import top.wpaint.pymov.mapper.OccupationDictMapper;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private final OccupationDictMapper occupationDictMapper;

    @GetMapping("occupationList")
    public R<List<OccupationVo>> listOccupation() {
        return R.ok(occupationDictMapper.listAll());
    }

    @PostMapping("")
    public R<Void> setUserLikes(List<Long> userLikes) {
        return R.ok();
    }
}
