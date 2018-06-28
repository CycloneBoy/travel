package com.cycloneboy.bookstore.service.impl;

import com.cycloneboy.bookstore.entity.News;
import com.cycloneboy.bookstore.mapper.NewsDao;
import com.cycloneboy.bookstore.service.NewsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cycloneboy
 * @since 2018-04-05
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsDao, News> implements NewsService {

}
