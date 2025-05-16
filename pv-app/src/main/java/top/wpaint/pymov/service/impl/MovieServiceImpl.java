package top.wpaint.pymov.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.wpaint.pymov.mapper.MovieMapper;
import top.wpaint.pymov.model.dto.movie.MovieQueryDto;
import top.wpaint.pymov.model.entity.Movie;
import top.wpaint.pymov.model.vo.movie.MovieVo;
import top.wpaint.pymov.service.MovieService;

import java.util.Arrays;
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

    @Override
    public MovieVo convertToVo(Movie movie) {
        MovieVo vo = new MovieVo();
        vo.setId(movie.getId());
        vo.setMovieId(movie.getMovieId());
        vo.setName(movie.getName());
        vo.setAlias(movie.getAlias());
        vo.setCover(movie.getCover());
        vo.setDoubanScore(movie.getDoubanScore());
        vo.setDoubanVotes(movie.getDoubanVotes());
        vo.setImdbId(movie.getImdbId());
        vo.setMins(movie.getMins());
        vo.setOfficialSite(movie.getOfficialSite());
        vo.setReleaseDate(movie.getReleaseDate());
        vo.setSlug(movie.getSlug());
        vo.setStoryline(movie.getStoryline());
        vo.setTags(movie.getTags());
        vo.setYear(movie.getYear());

        // 演员
        if (StrUtil.isNotBlank(movie.getActors())) {
            vo.setActors(List.of(movie.getActors().split("/")));
        }

        // 导演
        if (StrUtil.isNotBlank(movie.getDirectors())) {
            vo.setDirectors(List.of(movie.getDirectors().split("/")));
        }

        // 类型
        if (StrUtil.isNotBlank(movie.getGenres())) {
            vo.setGenres(List.of(movie.getGenres().split("/")));
        }

        // 语言
        if (StrUtil.isNotBlank(movie.getLanguages())) {
            // 数据集的原因，需要为每个 language 执行 trim
            List<String> languages = Arrays.stream(movie.getLanguages().split("/"))
                    .map(String::trim)
                    .toList();
            vo.setLanguages(languages);
        }

        // 地区
        if (StrUtil.isNotBlank(movie.getRegions())) {
            // 数据集的原因，需要为每个 region 执行 trim
            List<String> regions = Arrays.stream(movie.getRegions().split("/"))
                    .map(String::trim)
                    .toList();
            vo.setLanguages(regions);
        }


        return vo;
    }
}




