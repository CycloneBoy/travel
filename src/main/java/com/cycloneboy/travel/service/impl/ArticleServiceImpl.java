package com.cycloneboy.travel.service.impl;

import com.cycloneboy.travel.entity.Article;
import com.cycloneboy.travel.mapper.ArticleDao;
import com.cycloneboy.travel.service.ArticleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cycloneboy
 * @since 2018-07-05
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService {

}
