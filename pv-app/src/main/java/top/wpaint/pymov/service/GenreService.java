package top.wpaint.pymov.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.wpaint.pymov.model.entity.Genre;
import top.wpaint.pymov.model.vo.movie.GenreVo;

import java.util.List;

/**
 * @author tecwds
 * @description 针对表【pv_genre】的数据库操作Service
 * @createDate 2025-05-13 17:51:07
 */
public interface GenreService extends IService<Genre> {

    /**
     * 获取所有 genre
     * @return genre 列表
     */
    List<GenreVo> getAllGenre();
}
