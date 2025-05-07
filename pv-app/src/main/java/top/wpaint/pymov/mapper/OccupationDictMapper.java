package top.wpaint.pymov.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.wpaint.pymov.domain.OccupationDict;
import top.wpaint.pymov.domain.vo.OccupationVo;

import java.util.List;

/**
* @author tecwds
* @description 针对表【pv_occupation_dict】的数据库操作Mapper
* @createDate 2025-05-01 21:54:06
* @Entity top.wpaint.pymov.domain.OccupationDict
*/
@Mapper
public interface OccupationDictMapper {

    int selectOneByOccupationId(Integer occupationId);

    List<OccupationVo> listAll();
}




