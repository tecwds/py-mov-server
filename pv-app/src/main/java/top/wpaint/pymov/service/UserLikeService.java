package top.wpaint.pymov.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.wpaint.pymov.model.dto.user.like.LikeQueryDto;
import top.wpaint.pymov.model.entity.UserLike;
import top.wpaint.pymov.model.vo.user.like.LikeVo;

/**
 * @author tecwds
 * @description 针对表【pv_user_like】的数据库操作Service
 * @createDate 2025-05-13 17:51:07
 */
public interface UserLikeService extends IService<UserLike> {

    /**
     * 获取用户喜欢的电影类型分页
     *
     * @param likeQueryDto 查询参数
     * @return 分页数据
     */
    Page<LikeVo> getLikePage(LikeQueryDto likeQueryDto);
}
