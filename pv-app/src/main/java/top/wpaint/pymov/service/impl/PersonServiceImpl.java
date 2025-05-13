package top.wpaint.pymov.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.wpaint.pymov.mapper.PersonMapper;
import top.wpaint.pymov.model.entity.Person;
import top.wpaint.pymov.service.PersonService;

/**
 * @author tecwds
 * @description 针对表【pv_person】的数据库操作Service实现
 * @createDate 2025-05-13 17:51:07
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person>
        implements PersonService {

}




