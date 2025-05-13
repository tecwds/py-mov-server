package top.wpaint.pymov.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.wpaint.pymov.mapper.UserLikeMapper;
import top.wpaint.pymov.model.dto.user.like.LikeQueryDto;
import top.wpaint.pymov.model.entity.Genre;
import top.wpaint.pymov.model.entity.UserLike;
import top.wpaint.pymov.model.vo.user.like.LikeVo;
import top.wpaint.pymov.service.GenreService;
import top.wpaint.pymov.service.UserLikeService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tecwds
 * @description 针对表【pv_user_like】的数据库操作Service实现
 * @createDate 2025-05-13 17:51:07
 */
@Service
public class UserLikeServiceImpl extends ServiceImpl<UserLikeMapper, UserLike>
        implements UserLikeService {

    @Resource
    private GenreService genreService;

    @Override
    public Page<LikeVo> getLikePage(LikeQueryDto likeQueryDto) {
        String userId = likeQueryDto.getUserId();

        // 获得分页数据
        Page<UserLike> page = baseMapper.selectPage(
                new Page<>(likeQueryDto.getCurrent(), likeQueryDto.getPageSize()),
                new LambdaQueryWrapper<UserLike>().eq(UserLike::getUserId, userId));

        // 获得 genreId 和 genreName 的 Map 集合，便于后续转换 Vo
        List<Genre> genreList = genreService.list();

        Map<Long, Genre> genreMap = genreList.stream()
                .collect(Collectors.toMap(Genre::getId, genre -> genre));

        Page<LikeVo> voPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());

        voPage.setRecords(page.getRecords().stream().map(item -> {
            LikeVo vo = new LikeVo();
            vo.setGenreId(item.getGenreId());
            vo.setGenreName(genreMap.get(item.getGenreId()).getGenreName());
            return vo;
        }).toList());

        return voPage;

    }
}




