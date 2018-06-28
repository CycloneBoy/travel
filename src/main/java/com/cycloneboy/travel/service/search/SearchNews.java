package com.cycloneboy.bookstore.service.search;

import com.cycloneboy.bookstore.entity.dto.ServiceMultiResult;
import com.cycloneboy.bookstore.entity.dto.ServiceResult;

import java.util.List;

/**
 * 检索接口
 *
 */
public interface SearchNews {


    /**
     * 索引目标新闻
     * @param newsId
     */
    void index(Long newsId);

    /**
     * 移除新闻索引
     * @param newsId
     */
    void remove(Long newsId);

    /**
     * 查询新闻接口
     * @param rentSearch
     * @return
     */
    ServiceMultiResult<Long> query(NewsSearch newsSearch);

    /**
     * 获取补全建议关键词
     */
    ServiceResult<List<String>> suggest(String prefix);

    /**
     * 聚合特定小区的房间数
     */
    ServiceResult<Long> aggregateDistrictHouse(String cityEnName, String regionEnName, String district);

    /**
     * 聚合城市数据
     * @param cityEnName
     * @return
     */
    ServiceMultiResult<NewsBucketDTO> mapAggregate(String cityEnName);

    /**
     * 城市级别查询
     * @return
     */
    ServiceMultiResult<Long> mapQuery(String cityEnName, String orderBy,
                                      String orderDirection, int start, int size);

}
