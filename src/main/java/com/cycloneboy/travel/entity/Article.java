package com.cycloneboy.travel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: travel
 * @description: Article文章实体类
 * @author: cycloneboy
 * @create:2018-04-02 08:41
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Document(indexName = "article", type = "article")
public class Article {

//    @Id
    private String id;
    private String title;
    private String author;
    private String releaseDate;
}
