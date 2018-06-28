package com.cycloneboy.bookstore.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cycloneboy.bookstore.entity.GraduationNews;
import com.cycloneboy.bookstore.entity.News;
import com.cycloneboy.bookstore.entity.dto.PageQueryDTO;
import com.cycloneboy.bookstore.entity.dto.PageResultDTO;
import com.cycloneboy.bookstore.service.GraduationNewsService;
import com.cycloneboy.bookstore.service.NewsService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: bookstore
 * @description:
 * @author: cycloneboy
 * @create:2018-04-09 15:50
 **/
@RestController
@RequestMapping("/bookstore/search")
public class NewsSearchController {

    private static final  Logger logger = LoggerFactory.getLogger(NewsSearchController.class);

    @Autowired
    private NewsService newsService;

//   @Autowired
//    private GraduationNewsService graduationNewsService;
//
//    /**
//     * 获取News列表
//     */
//    @ApiOperation(value = "获取News列表", notes = "获取News列表", httpMethod = "POST")
//    @PostMapping("news")
//    public PageResultDTO loadPage(@RequestBody PageQueryDTO params){
//
//        List<GraduationNews> graduationNewsList =  graduationNewsService.searchGraduationNews(
//                                                        params.getPage(),params.getSize(),
//                                                        params.getQuery().toString());
//
//        logger.info("从Elasticsearch中 获取News列表总数：" + graduationNewsList.size());
//
//        return new PageResultDTO((long)graduationNewsList.size(),graduationNewsList);
//    }

}
