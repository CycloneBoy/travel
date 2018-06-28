package com.cycloneboy.bookstore.mapper;



import com.cycloneboy.bookstore.entity.PubmedNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PubmedNewsRepository extends ElasticsearchRepository<PubmedNews, String> {

    Page<PubmedNews> findById(String id, Pageable pageable);

    List<PubmedNews> findByTitle(String title);


}
