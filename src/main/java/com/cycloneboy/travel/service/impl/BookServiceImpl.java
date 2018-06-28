package com.cycloneboy.bookstore.service.impl;

import com.cycloneboy.bookstore.entity.Book;
import com.cycloneboy.bookstore.mapper.BookDao;
import com.cycloneboy.bookstore.service.BookService;
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
