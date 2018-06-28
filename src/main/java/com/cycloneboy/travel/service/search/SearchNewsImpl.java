package com.cycloneboy.bookstore.service.search;

import com.cycloneboy.bookstore.entity.dto.ServiceMultiResult;
import com.cycloneboy.bookstore.entity.dto.ServiceResult;
import com.cycloneboy.bookstore.mapper.GraduationNewsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.swagger2.mappers.ModelMapper;

import java.util.List;

/**
 * @program: bookstore
 * @description:
 * @author: cycloneboy
 * @create:2018-04-09 01:00
 **/
public class SearchNewsImpl implements SearchNews{

    private static final Logger logger = LoggerFactory.getLogger(SearchNewsImpl.class);

    private static final String INDEX_NAME = "news";

    private static final String INDEX_TYPE = "news";

    private static final String INDEX_TOPIC = "house_build";


    @Autowired
    private GraduationNewsRepository graduationNewsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;


    /**
     * 索引目标新闻
     *
     * @param newsId
     */
    @Override
    public void index(Long newsId) {

    }

    /**
     * 移除新闻索引
     *
     * @param newsId
     */
    @Override
    public void remove(Long newsId) {

    }

    /**
     * 查询新闻接口
     *
     * @param newsSearch@return
     */
    @Override
    public ServiceMultiResult<Long> query(NewsSearch newsSearch) {
        return null;
    }

    /**
     * 获取补全建议关键词
     *
     * @param prefix
     */
    @Override
    public ServiceResult<List<String>> suggest(String prefix) {

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();



        return null;
    }

    /**
     * 聚合特定小区的房间数
     *
     * @param cityEnName
     * @param regionEnName
     * @param district
     */
    @Override
    public ServiceResult<Long> aggregateDistrictHouse(String cityEnName, String regionEnName, String district) {
        return null;
    }

    /**
     * 聚合城市数据
     *
     * @param cityEnName
     * @return
     */
    @Override
    public ServiceMultiResult<NewsBucketDTO> mapAggregate(String cityEnName) {
        return null;
    }

    /**
     * 城市级别查询
     *
     * @param cityEnName
     * @param orderBy
     * @param orderDirection
     * @param start
     * @param size
     * @return
     */
    @Override
    public ServiceMultiResult<Long> mapQuery(String cityEnName, String orderBy, String orderDirection, int start, int size) {
        return null;
    }
}
