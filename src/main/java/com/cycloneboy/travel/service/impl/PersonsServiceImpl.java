package com.cycloneboy.bookstore.service.impl;

import com.cycloneboy.bookstore.entity.Persons;
import com.cycloneboy.bookstore.mapper.PersonsDao;
import com.cycloneboy.bookstore.service.PersonsService;
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
