package com.cycloneboy.travel.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.*;
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
@TableName("category")
public class Category extends Model<Category> {

private static final long serialVersionUID = 1L;

    /**
     * 标签ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 标签名称
     */
        private String label;


@Override
protected Serializable pkVal() {
            return this.id;
        }

        }
