package com.cycloneboy.travel.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author cycloneboy
 * @since 2018-07-05
 */
@Data
@TableName("article")
public class Article extends Model<Article> {

private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
                private Integer id;
    /**
     * 文章名称
     */
        private String title;
    /**
     * 文章摘要
     */
        private String summary;
    /**
     * 文章栏目
     */
    @TableField("category_id")
        private Integer categoryId;
    /**
     * 文章内容
     */
        private String content;
    /**
     * 文章封面图片
     */
        private String image;
    /**
     * 文章被点赞的次数
     */
    @TableField("like_count")
        private Integer likeCount;
    /**
     * 文章被评论的次数
     */
    @TableField("comment_count")
        private Integer commentCount;
    /**
     * 文章被浏览的次数
     */
    @TableField("view_count")
        private Integer viewCount;
    /**
     * 文章作者ID
     */
    @TableField("user_id")
        private Integer userId;
    /**
     * 文章状态
     */
        private Integer status;
    /**
     * 创建时间
     */
    @TableField("create_time")
        private Date createTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
        private Date updateTime;


@Override
protected Serializable pkVal() {
            return this.id;
        }

        }
