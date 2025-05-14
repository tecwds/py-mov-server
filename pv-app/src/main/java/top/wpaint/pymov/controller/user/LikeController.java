package top.wpaint.pymov.controller.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wpaint.pymov.common.R;
import top.wpaint.pymov.common.RCode;
import top.wpaint.pymov.model.dto.user.like.LikeAddDto;
import top.wpaint.pymov.model.dto.user.like.LikeDeleteDto;
import top.wpaint.pymov.model.dto.user.like.LikeEditDto;
import top.wpaint.pymov.model.dto.user.like.LikeQueryDto;
import top.wpaint.pymov.model.entity.UserLike;
import top.wpaint.pymov.model.vo.user.like.LikeVo;
import top.wpaint.pymov.service.UserLikeService;
import top.wpaint.pymov.utils.ThrowUtils;
import top.wpaint.pymov.utils.ValidUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SaCheckLogin
@RestController
@RequestMapping("user/like")
public class LikeController {

    @Resource
    private UserLikeService userLikeService;

    /**
     * 获取用户收藏列表
     *
     * @param likeQueryDto 查询条件
     * @return 分页数据
     */
    @PostMapping("list")
    public R<Page<LikeVo>> likePage(@RequestBody LikeQueryDto likeQueryDto) {
        ValidUtils.requireNotNull(likeQueryDto);
        return R.ok(userLikeService.getLikePage(likeQueryDto));
    }

    /**
     * 添加用户收藏
     *
     * @param likeAddDto 收藏信息
     * @return 无
     */
    @PostMapping("add")
    public R<Void> addLike(@RequestBody LikeAddDto likeAddDto) {
        ValidUtils.requireNotNull(likeAddDto);
        boolean res = userLikeService.saveBatch(likeAddDto.getLikeGenreIds()
                .stream()
                .map(i -> {
                    UserLike ul = new UserLike();
                    ul.setUserId(likeAddDto.getUserId());
                    ul.setGenreId(i);
                    return ul;
                })
                .collect(Collectors.toList()));
        ThrowUtils.throwIf(!res, RCode.OPERATION_ERROR, "添加失败");
        return R.ok();
    }

    /**
     * 编辑用户收藏
     *
     * @param likeEditDto 收藏信息
     * @return 无
     */
    @PostMapping("edit")
    public R<Void> editLike(@RequestBody LikeEditDto likeEditDto) {
        ValidUtils.requireNotNull(likeEditDto);
        ThrowUtils.throwIf(CollUtil.isEmpty(likeEditDto.getGenreIds()), RCode.PARAMS_ERROR);

        List<UserLike> collect = likeEditDto.getGenreIds()
                .stream()
                .map(i -> {
                    UserLike ul = new UserLike();
                    ul.setUserId(likeEditDto.getUserId());
                    ul.setGenreId(i);
                    return ul;
                })
                .toList();

        // 先删除原有的数据
        userLikeService.remove(new LambdaQueryWrapper<UserLike>().eq(UserLike::getUserId, likeEditDto.getUserId()));

        // 重新添加数据
        boolean res = userLikeService.saveBatch(collect);

        ThrowUtils.throwIf(!res, RCode.OPERATION_ERROR, "修改失败");
        return R.ok();
    }

    /**
     * 删除用户收藏
     *
     * @param likeDeleteDto 删除信息
     * @return 无
     */
    @PostMapping("delete")
    public R<Void> deleteLike(@RequestBody LikeDeleteDto likeDeleteDto) {
        ValidUtils.requireNotNull(likeDeleteDto);

        // 删除
        boolean res = userLikeService.remove(new LambdaQueryWrapper<UserLike>()
                .eq(UserLike::getUserId, likeDeleteDto.getUserId())
                .in(UserLike::getGenreId, likeDeleteDto.getGenreIds()));

        ThrowUtils.throwIf(!res, RCode.OPERATION_ERROR, "删除失败");
        return R.ok();
    }

}
