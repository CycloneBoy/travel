package com.cycloneboy.travel.service.impl;

import com.cycloneboy.travel.entity.Book;
import com.cycloneboy.travel.mapper.BookDao;
import com.cycloneboy.travel.service.BookService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cycloneboy
 * @since 2018-03-16
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements BookService {

}
