package top.wpaint.pymov.controller.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.micrometer.core.instrument.config.validate.Validated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wpaint.pymov.common.R;
import top.wpaint.pymov.model.dto.user.like.LikeAddDto;
import top.wpaint.pymov.model.dto.user.like.LikeQueryDto;
import top.wpaint.pymov.model.vo.user.like.LikeVo;
import top.wpaint.pymov.service.UserLikeService;
import top.wpaint.pymov.utils.ValidUtils;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("user/like")
public class LikeController {

    @Resource
    private UserLikeService userLikeService;

    /**
     * 获取用户收藏列表
     *
     * @param likeQueryDto 查询条件
     * @return
     */
    @PostMapping("list")
    public R<Page<LikeVo>> likePage(LikeQueryDto likeQueryDto) {
        ValidUtils.requireNotNull(likeQueryDto);
        return R.ok(userLikeService.getLikePage(likeQueryDto));
    }

    @PostMapping("add")
    public R<Void> addLike(LikeAddDto likeAddDto) {
        ValidUtils.requireNotNull(likeAddDto);
        return R.ok();
    }

}
