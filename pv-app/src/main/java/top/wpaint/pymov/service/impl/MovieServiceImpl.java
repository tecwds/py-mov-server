package top.wpaint.pymov.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.wpaint.pymov.common.QueryRange;
import top.wpaint.pymov.mapper.MovieMapper;
import top.wpaint.pymov.model.dto.movie.MovieQueryDto;
import top.wpaint.pymov.model.entity.Movie;
import top.wpaint.pymov.service.MovieService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static top.wpaint.pymov.utils.QueryUtils.queryListHelper;
import static top.wpaint.pymov.utils.QueryUtils.queryRangeHelper;


/**
 * @author tecwds
 * @description 针对表【pv_movie】的数据库操作Service实现
 * @createDate 2025-05-13 17:51:07
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie>
        implements MovieService {

    @Override
    public Page<Movie> getPage(MovieQueryDto movieQueryDto) {
        LambdaQueryWrapper<Movie> query = getQueryWrapper(movieQueryDto);

        return page(
                new Page<>(movieQueryDto.getCurrent(), movieQueryDto.getPageSize()),
                query
        );
    }

    @Override
    public LambdaQueryWrapper<Movie> getQueryWrapper(MovieQueryDto movieQueryDto) {
        LambdaQueryWrapper<Movie> query = new LambdaQueryWrapper<>();

        Long id = movieQueryDto.getId();
        String movieId = movieQueryDto.getMovieId();
        String name = movieQueryDto.getName();
        String alias = movieQueryDto.getAlias();

        query
                .eq(id != null, Movie::getId, id)
                .eq(movieId != null, Movie::getMovieId, movieId)
                .like(name != null, Movie::getName, name)
                .like(alias != null, Movie::getAlias, alias);

        queryListHelper(query, movieQueryDto.getActors(), Movie::getActors);
        queryListHelper(query, movieQueryDto.getDirectors(), Movie::getDirectors);
        queryListHelper(query, movieQueryDto.getGenreNames(), Movie::getGenres);
        queryListHelper(query, movieQueryDto.getLanguages(), Movie::getLanguages);
        queryListHelper(query, movieQueryDto.getRegions(), Movie::getRegions);
        queryListHelper(query, movieQueryDto.getTags(), Movie::getTags);

        queryRangeHelper(query, movieQueryDto.getDoubanScoreRange(), Movie::getDoubanScore);
        queryRangeHelper(query, movieQueryDto.getMinsRange(), Movie::getMins);
        queryRangeHelper(query, movieQueryDto.getReleaseDateRange(), Movie::getReleaseDate);
        queryRangeHelper(query, movieQueryDto.getYear(), Movie::getYear);

        return query;
    }
}




