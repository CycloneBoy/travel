package com.cycloneboy.travel.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author cycloneboy
 * @since 2018-04-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("news")
public class News extends Model<News> {

private static final long serialVersionUID = 1L;

    /**
     * ID
     */
     private Integer id;


    /**
     * 名称
     */
        private String title;
    /**
     * 内容
     */
        private String content;

    /**
     * 编号
     */
    private String number;
    /**
     * 发布时间
     */
    private Date date;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 分类
     */
    private String tag;

    /**
     * 关键词
     */
    private String keyword;


@Override
protected Serializable pkVal() {
            return this.id;
        }

        }
