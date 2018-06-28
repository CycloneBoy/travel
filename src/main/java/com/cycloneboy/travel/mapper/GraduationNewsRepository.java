package com.cycloneboy.bookstore.mapper;



import com.cycloneboy.bookstore.entity.GraduationNews;
import com.cycloneboy.bookstore.entity.PubmedNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface GraduationNewsRepository extends ElasticsearchRepository<GraduationNews, String> {

    Page<GraduationNews> findById(String id, Pageable pageable);

    List<GraduationNews> findByTitle(String title);

}
