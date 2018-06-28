package com.cycloneboy.bookstore.service;

import com.cycloneboy.bookstore.entity.GraduationNews;
import com.cycloneboy.bookstore.entity.PubmedNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface GraduationNewsService {

    GraduationNews save(GraduationNews graduationNews);

    void delete(GraduationNews graduationNews);

    GraduationNews findOne(String id);

    Iterable<GraduationNews> findAll();

    Page<GraduationNews> findById(String id, PageRequest pageRequest);

    List<GraduationNews> findByTitle(String title);

    List<GraduationNews> searchGraduationNews(int pageNumber, int pageSize, String searchContent);


}
