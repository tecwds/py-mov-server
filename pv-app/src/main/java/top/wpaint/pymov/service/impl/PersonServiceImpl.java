package top.wpaint.pymov.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wpaint.pymov.model.entity.Person;
import top.wpaint.pymov.service.PersonService;
import top.wpaint.pymov.mapper.PersonMapper;
import org.springframework.stereotype.Service;

/**
* @author tecwds
* @description 针对表【pv_person】的数据库操作Service实现
* @createDate 2025-05-13 17:51:07
*/
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person>
    implements PersonService{

}




