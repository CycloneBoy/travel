package com.cycloneboy.bookstore.service.impl;

import com.cycloneboy.bookstore.entity.PubmedNews;
import com.cycloneboy.bookstore.mapper.PubmedNewsRepository;
import com.cycloneboy.bookstore.service.PubmedNewsService;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.common.lucene.search.function.FiltersFunctionScoreQuery;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import static org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.suggest.term.TermSuggestion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;




import java.io.IOException;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * @program: bookstore
 * @description: PubmedNewsServiceImpl
 * @author: cycloneboy
 * @create:2018-04-04 10:48
 **/
@Service
public class PubmedNewsServiceImpl implements PubmedNewsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PubmedNewsServiceImpl.class);

    @Autowired
    private PubmedNewsRepository pubmedNewsRepository;

    int PAGE_SIZE = 15; //默认分页大小

    int PAGE_NUMBER = 0; //默认当前分页

    String SCORE_MODE_SUM = "sum"; //权重分求和模式

    Float MIN_SCORE = 10.0F; //由于无相关性的分值默认为1， 设置权重分最小值为10


    @Override
    public PubmedNews save(PubmedNews pubmedNews) {
        return pubmedNewsRepository.save(pubmedNews);
    }

    @Override
    public void delete(PubmedNews pubmedNews) {
        pubmedNewsRepository.delete(pubmedNews);
    }

    @Override
    public PubmedNews findOne(String id) {
        return pubmedNewsRepository.findById(id).get();
    }

    @Override
    public Iterable<PubmedNews> findAll() {
        return pubmedNewsRepository.findAll();
    }

    @Override
    public Page<PubmedNews> findById(String id, PageRequest pageRequest) {
        return pubmedNewsRepository.findById(id,pageRequest);
    }

    @Override
    public List<PubmedNews> findByTitle(String title) {
        return pubmedNewsRepository.findByTitle(title);
    }

    @Override
    public List<PubmedNews> searchPubmedNews(int pageNumber, int pageSize, String searchContent) {

        if(pageSize==0) {
            pageSize = PAGE_SIZE;
        }
        if(pageNumber<0) {
            pageNumber = PAGE_NUMBER;
        }

        SearchQuery searchQuery = getEntitySearchQuery(pageNumber,pageSize,searchContent);

        LOGGER.info("\n searchCity: searchContent [" + searchContent + "] \n DSL  = \n "
                + searchQuery.getQuery().toString());


        Page<PubmedNews> pubmedNewsPage = pubmedNewsRepository.search(searchQuery);

        return pubmedNewsPage.getContent();
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
                        matchQuery("name",searchContent),weightFactorFunction(1000))
        };

        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(functionBuilders)
                .scoreMode(FiltersFunctionScoreQuery.ScoreMode.SUM)
                .setMinScore(MIN_SCORE);

        //设置分页，否则只能按照ES默认的分页给
        Pageable pageable = PageRequest.of(pageNumber,pageSize);

        return new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();
    }

}
