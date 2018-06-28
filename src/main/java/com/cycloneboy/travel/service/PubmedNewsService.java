package com.cycloneboy.bookstore.service;

import com.cycloneboy.bookstore.entity.Article;
import com.cycloneboy.bookstore.entity.PubmedNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PubmedNewsService {

    PubmedNews save(PubmedNews pubmedNews);

    void delete(PubmedNews pubmedNews);

    PubmedNews findOne(String id);

    Iterable<PubmedNews> findAll();

    Page<PubmedNews> findById(String id, PageRequest pageRequest);

    List<PubmedNews> findByTitle(String title);

    List<PubmedNews> searchPubmedNews(int pageNumber, int pageSize, String searchContent);


}
