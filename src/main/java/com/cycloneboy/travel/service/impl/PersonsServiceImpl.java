package com.cycloneboy.travel.service.impl;

import com.cycloneboy.travel.entity.Persons;
import com.cycloneboy.travel.mapper.PersonsDao;
import com.cycloneboy.travel.service.PersonsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cycloneboy
 * @since 2018-03-15
 */
@Service
public class PersonsServiceImpl extends ServiceImpl<PersonsDao, Persons> implements PersonsService {

}
