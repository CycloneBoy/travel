package com.cycloneboy.travel.entity.dto;

import com.cycloneboy.travel.entity.Timeline;
import lombok.Data;

import java.util.Date;

/**
 * @program: travel
 * @description: 时间线传输层
 * @author: cycloneboy
 * @create:2018-07-09 17:20
 **/
@Data
public class TimelineDTO {

    /**
     * 时间
     */
    private Date time;
    /**
     * 图片
     */
    private String img;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;


    public TimelineDTO(Timeline timeline) {
        this.time = timeline.getCreateTime();
        // 获取截断的可显示的static路径
        if (timeline.getImage() != null && timeline.getImage().length() > 20){
            this.img = timeline.getImage().substring(20);
        }else {
            this.img = "/static/upload/file/20180709150031_5.jpg";
        }

        this.title = timeline.getTitle();
        this.content = timeline.getContent();
    }
}
