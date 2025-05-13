package top.wpaint.pymov.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wpaint.pymov.model.entity.Movie;
import top.wpaint.pymov.service.MovieService;
import top.wpaint.pymov.mapper.MovieMapper;
import org.springframework.stereotype.Service;

/**
* @author tecwds
* @description 针对表【pv_movie】的数据库操作Service实现
* @createDate 2025-05-13 17:20:37
*/
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie>
    implements MovieService{

}




