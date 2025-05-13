package top.wpaint.pymov.controller.movie;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.wpaint.pymov.common.R;
import top.wpaint.pymov.model.dto.movie.MovieQueryDto;
import top.wpaint.pymov.model.entity.Movie;
import top.wpaint.pymov.service.MovieService;
import top.wpaint.pymov.utils.ValidUtils;

@Slf4j
@RestController
@RequestMapping("movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    /**
     * 分页查询
     * @param dto 分页查询条件
     * @return 分页数据
     */
    @PostMapping("page")
    public R<Page<Movie>> getPage(@RequestBody MovieQueryDto dto) {
        ValidUtils.requireNotNull(dto);
        return R.ok(movieService.getPage(dto));
    }
}
