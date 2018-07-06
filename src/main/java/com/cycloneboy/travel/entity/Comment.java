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
 * <p>
 * </p>
 *
 * @author cycloneboy
 * @since 2018-07-05
 */
@Data
@TableName("comment")
public class Comment extends Model<Comment> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论者ID
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 被评论的实体ID
     */
    @TableField("entity_id")
    private Integer entityId;
    /**
     * 被评论的实体的类型
     */
    @TableField("entity_type")
    private Integer entityType;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 评论状态
     */
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
