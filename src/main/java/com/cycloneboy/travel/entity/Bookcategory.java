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
 * @since 2018-03-24
 */
@Data
@TableName("bookcategory")
public class Bookcategory extends Model<Bookcategory> {

private static final long serialVersionUID = 1L;

    /**
     * ID
     */
                    @TableId(value = "id", type = IdType.AUTO)
                private Integer id;
    /**
     * 书籍ID
     */
    @TableField("book_id")
        private Integer bookId;
    /**
     * 书籍名称
     */
    @TableField("book_title")
        private String bookTitle;
    /**
     * 书籍作者ID
     */
    @TableField("authors_id")
        private Integer authorsId;
    /**
     * 作者名称
     */
    @TableField("authors_name")
        private String authorsName;
    /**
     * 标签ID
     */
    @TableField("category_id")
        private Integer categoryId;
    /**
     * 标签名称
     */
    @TableField("category_label")
        private String categoryLabel;
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
