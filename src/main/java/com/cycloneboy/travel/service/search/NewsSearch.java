package com.cycloneboy.travel.service.search;

import lombok.Data;

import java.util.Date;

/**
 * @program: travel
 * @description: 新闻请求参数结构体
 * @author: cycloneboy
 * @create:2018-04-09 00:47
 **/
@Data
public class NewsSearch {

    private String id;
    private String title;
    private String content;
    private Date date;
    private Integer type;
    private String tag;
    private String keyword;

    private String orderBy = "lastUpdateTime";
    private String orderDirection = "desc";
    private int start = 0;
    private int size = 5;

    public int getStart() {
        return start > 0 ? start : 0;
    }

    public int getSize() {
        if (this.size < 1) {
            return 5;
        } else if (this.size > 100) {
            return 100;
        } else {
            return this.size;
        }
    }

}
