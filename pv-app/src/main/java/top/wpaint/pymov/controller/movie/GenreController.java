package top.wpaint.pymov.controller.movie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wpaint.pymov.common.R;
import top.wpaint.pymov.model.vo.movie.GenreVo;
import top.wpaint.pymov.service.GenreService;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("genre")
public class GenreController {

    @Resource
    private GenreService genreService;

    /**
     * 获取所有电影类型
     *
     * @return 所有电影类型列表
     */
    @GetMapping("list")
    public R<List<GenreVo>> listAllGenre() {
        return R.ok(genreService.getAllGenre());
    }
}
