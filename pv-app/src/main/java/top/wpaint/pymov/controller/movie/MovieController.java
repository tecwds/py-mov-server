package top.wpaint.pymov.controller.movie;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.wpaint.pymov.common.R;
import top.wpaint.pymov.common.RCode;
import top.wpaint.pymov.model.dto.movie.MovieQueryDto;
import top.wpaint.pymov.model.entity.Movie;
import top.wpaint.pymov.model.vo.movie.MovieVo;
import top.wpaint.pymov.service.MovieService;
import top.wpaint.pymov.service.UserService;
import top.wpaint.pymov.utils.ThrowUtils;
import top.wpaint.pymov.utils.ValidUtils;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("movie")
@RequiredArgsConstructor
public class MovieController {

    private final UserService userService;
    private final MovieService movieService;

    /**
     * 分页查询
     *
     * @param dto 分页查询条件
     * @return 分页数据
     */
    @PostMapping("page")
    public R<Page<Movie>> getPage(@RequestBody MovieQueryDto dto) {
        ValidUtils.requireNotNull(dto);
        return R.ok(movieService.getPage(dto));
    }

    /**
     * 获取某个用户的推荐电影列表
     *
     * @param userId 用户 ID
     * @param topN   需要推荐的电影数量，默认推荐 20 条
     * @return 电影列表
     */
    @GetMapping("recommend")
    public R<List<MovieVo>> getRecommendList(@RequestParam String userId, @RequestParam(defaultValue = "20", required = false) Integer topN) {
        ValidUtils.requireNotNull(userId);

        // 为了便捷就直接在这儿封装类了
        @Data
        class RecommendRes {
            private String status;
            private String message;
            private ResData data;

            @Data
            static class ResData {
                private String userId;
                private Integer count;
                private List<String> movieIds;
            }
        }

        // String recommend = HttpUtil.get("http://pv-recommend:5000/api/recommend?user_id=" + userId + "&top_n=" + topN);
        String recommend = HttpUtil.get("http://localhost:5000/api/recommend?user_id=" + userId + "&top_n=" + topN);
        RecommendRes res = JSONUtil.toBean(recommend, RecommendRes.class, true);

        log.info("推荐获取结果: {}", res);

        ThrowUtils.throwIf(res == null || res.data == null, RCode.SYSTEM_ERROR, "推荐生成失败");

        // 获取所有电影数据并转换成 vo 对象
        QueryWrapper<Movie> query = new QueryWrapper<>();

        // 使用 in 语句出错，改成手动
        if (CollUtil.isNotEmpty(res.data.movieIds)) {
            res.data.movieIds.forEach(id -> {
                query.lambda().or().eq(Movie::getMovieId, id);
            });
        }
        List<Movie> movies = movieService.list(query);

        List<MovieVo> voList = movies.stream().map(movieService::convertToVo).toList();

        return R.ok(voList);
    }
}
