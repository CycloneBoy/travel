package com.cycloneboy.bookstore.service.impl;

import com.cycloneboy.bookstore.entity.GraduationNews;
import com.cycloneboy.bookstore.mapper.GraduationNewsRepository;

import com.cycloneboy.bookstore.service.GraduationNewsService;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.lucene.search.function.FiltersFunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders.weightFactorFunction;

/**
 * @program: bookstore
 * @description: GraduationNewsServiceImpl
 * @author: cycloneboy
 * @create:2018-04-04 10:48
 **/
@Service
public class GraduationNewsServiceImpl implements GraduationNewsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GraduationNewsServiceImpl.class);

    @Autowired
    private GraduationNewsRepository graduationNewsRepository;

    int PAGE_SIZE = 100; //默认分页大小

    int PAGE_NUMBER = 1; //默认当前分页

    String SCORE_MODE_SUM = "sum"; //权重分求和模式

    Float MIN_SCORE = 10.0F; //由于无相关性的分值默认为1， 设置权重分最小值为10


    @Override
    public GraduationNews save(GraduationNews graduationNews) {
        return graduationNewsRepository.save(graduationNews);
    }

    @Override
    public void delete(GraduationNews graduationNews) {
        graduationNewsRepository.delete(graduationNews);
    }

    @Override
    public GraduationNews findOne(String id) {
        return graduationNewsRepository.findById(id).get();
    }

    @Override
    public Iterable<GraduationNews> findAll() {
        return graduationNewsRepository.findAll();
    }

    @Override
    public Page<GraduationNews> findById(String id, PageRequest pageRequest) {
        return graduationNewsRepository.findById(id,pageRequest);
    }

    @Override
    public List<GraduationNews> findByTitle(String title) {
        return graduationNewsRepository.findByTitle(title);
    }

    @Override
    public List<GraduationNews> searchGraduationNews(int pageNumber, int pageSize, String searchContent) {

        if(pageSize==0) {
            pageSize = PAGE_SIZE;
        }
        if(pageNumber<0) {
            pageNumber = PAGE_NUMBER;
        }

        SearchQuery searchQuery = getEntitySearchQuery(pageNumber,pageSize,searchContent);

        LOGGER.info("\n searchCity: searchContent [" + searchContent + "] \n DSL  = \n "
                + searchQuery.getQuery().toString());


        Page<GraduationNews> graduationNewsPage = graduationNewsRepository.search(searchQuery);

        return graduationNewsPage.getContent();
    }

    /**
     * 组装搜索Query对象
     * @param pageNumber
     * @param pageSize
     * @param searchContent
     * @return
     */
    private SearchQuery getEntitySearchQuery(int pageNumber, int pageSize, String searchContent) {


        FunctionScoreQueryBuilder.FilterFunctionBuilder []  functionBuilders = {
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                        matchQuery("title.pinyin",searchContent),weightFactorFunction(1000))
        };

        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(functionBuilders)
                .scoreMode(FiltersFunctionScoreQuery.ScoreMode.SUM)
                .setMinScore(MIN_SCORE);

        //设置分页，否则只能按照ES默认的分页给
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("title.pinyin",searchContent);


        HighlightBuilder.Field field = new HighlightBuilder.Field("title.pinyin");

        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
        searchQueryBuilder.withPageable(pageable);
        searchQueryBuilder.withQuery(matchQueryBuilder);
        searchQueryBuilder.withHighlightFields(field);



//        return searchQueryBuilder.build();

        return new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder)
                .withHighlightFields(field)
                .build();
    }





}
