package top.wpaint.pymov.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.wpaint.pymov.mapper.GenreMapper;
import top.wpaint.pymov.model.entity.Genre;
import top.wpaint.pymov.model.vo.movie.GenreVo;
import top.wpaint.pymov.service.GenreService;

import java.util.List;

/**
 * @author tecwds
 * @description 针对表【pv_genre】的数据库操作Service实现
 * @createDate 2025-05-13 17:51:07
 */
@Service
public class GenreServiceImpl extends ServiceImpl<GenreMapper, Genre>
        implements GenreService {

    @Override
    public List<GenreVo> getAllGenre() {
        return list().stream().map(g -> {
            GenreVo vo = new GenreVo();
            vo.setId(g.getId());
            vo.setName(g.getGenreName());
            return vo;
        }).toList();
    }
}




