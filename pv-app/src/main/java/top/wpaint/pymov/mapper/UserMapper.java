package top.wpaint.pymov.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.wpaint.pymov.domain.User;

/**
* @author tecwds
* @description 针对表【pv_user】的数据库操作Mapper
* @createDate 2025-05-01 21:54:06
* @Entity top.wpaint.pymov.domain.User
*/
@Mapper
public interface UserMapper {

    User selectByUsername(String username);
}




