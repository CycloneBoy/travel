package com.cycloneboy.bookstore.service;

import com.cycloneboy.bookstore.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ArticleService {

    Article save(Article article);

    void delete(Article article);

    Article findOne(String id);

    Iterable<Article> findAll();

    Page<Article> findByAuthor(String author, PageRequest pageRequest);

    List<Article> findByTitle(String title);

}
