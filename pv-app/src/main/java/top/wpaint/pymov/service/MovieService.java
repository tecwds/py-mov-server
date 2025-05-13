package top.wpaint.pymov.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.wpaint.pymov.model.dto.movie.MovieQueryDto;
import top.wpaint.pymov.model.entity.Movie;

/**
 * @author tecwds
 * @description 针对表【pv_movie】的数据库操作Service
 * @createDate 2025-05-13 17:51:07
 */
public interface MovieService extends IService<Movie> {

    /**
     * 分页查询
     *
     * @param movieQueryDto 查询条件
     * @return 分页结果
     */
    Page<Movie> getPage(MovieQueryDto movieQueryDto);

    /**
     * 获取查询条件
     *
     * @param movieQueryDto 查询条件
     * @return QueryWrapper
     */
    LambdaQueryWrapper<Movie> getQueryWrapper(MovieQueryDto movieQueryDto);
}
