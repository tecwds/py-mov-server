package top.wpaint.pymov.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.wpaint.pymov.mapper.GenreMapper;
import top.wpaint.pymov.model.entity.Genre;
import top.wpaint.pymov.service.GenreService;

/**
* @author tecwds
* @description 针对表【pv_genre】的数据库操作Service实现
* @createDate 2025-05-13 17:51:07
*/
@Service
public class GenreServiceImpl extends ServiceImpl<GenreMapper, Genre>
    implements GenreService{

}




