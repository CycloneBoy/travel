package com.cycloneboy.bookstore.service.impl;

import com.cycloneboy.bookstore.entity.Article;
import com.cycloneboy.bookstore.mapper.ArticleRepository;
import com.cycloneboy.bookstore.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: bookstore
 * @description: ArticleService 实现类
 * @author: cycloneboy
 * @create:2018-04-02 08:48
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;


    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void delete(Article article) {
        articleRepository.delete(article);
    }

    @Override
    public Article findOne(String id) {
        return articleRepository.findById(id).get();
    }

    @Override
    public Iterable<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Page<Article> findByAuthor(String author, PageRequest pageRequest) {
        return articleRepository.findByAuthor(author,pageRequest);
    }

    @Override
    public List<Article> findByTitle(String title) {
        return articleRepository.findByTitle(title);
    }
}
