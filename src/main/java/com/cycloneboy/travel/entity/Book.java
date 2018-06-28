package com.cycloneboy.travel.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author cycloneboy
 * @since 2018-03-24
 */

@Data
@TableName("book")
public class Book extends Model<Book> {

private static final long serialVersionUID = 1L;

    /**
     * 书籍ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 书籍名称
     */
        private String title;
    /**
     * 书籍作者
     */
    @TableField("authors_id")
        private Integer authorsId;
    /**
     * 书籍栏目
     */
    @TableField("channel_id")
        private Integer channelId;
    /**
     * 书籍评分
     */
        private Float score;
    /**
     * 书籍评分数量
     */
    @TableField("score_count")
        private Integer scoreCount;
    /**
     * 书籍价格
     */
        private Integer price;
    /**
     * 书籍字数
     */
    @TableField("word_count")
        private Integer wordCount;
    /**
     * 书籍描述
     */
        private String content;
    /**
     * 最新章节
     */
    @TableField("latest_id")
        private Integer latestId;
    /**
     * 总结
     */
        private String summary;
    /**
     * 书籍完结 0：未完结 1：已完结
     */
        private Integer finish;
    /**
     * 最新
     */
        private String latest;
    /**
     * 章节数
     */
    @TableField("chapter_count")
        private Integer chapterCount;
    /**
     * 是否可免费阅读 0：可以 1：不可以
     */
    @TableField("allow_free_read")
        private Integer allowFreeRead;
    /**
     * 书籍评论数
     */
    @TableField("comment_count")
        private Integer commentCount;
    /**
     * 是否下架 0：没有 1：下架 2：待审核
     */
    @TableField("on_sale")
        private Integer onSale;
    /**
     * 版权
     */
        private String rights;
    /**
     * 封面
     */
        private String cover;
    /**
     * 书籍地址
     */
        private String url;
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

    public Book() {
    }


    @Override
protected Serializable pkVal() {
            return this.id;
        }

    protected boolean canEqual(Object other) {
        return other instanceof Book;
    }

}
