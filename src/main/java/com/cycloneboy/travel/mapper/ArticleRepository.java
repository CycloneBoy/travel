package com.cycloneboy.bookstore.mapper;

import com.cycloneboy.bookstore.entity.Article;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

    Page<Article> findByAuthor(String author, Pageable pageable);

    List<Article> findByTitle(String title);



}
