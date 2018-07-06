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
 * @since 2018-07-06
 */
@Data
@TableName("timeline")
public class Timeline extends Model<Timeline> {

private static final long serialVersionUID = 1L;

    /**
     * ID
     */
                    @TableId(value = "id", type = IdType.AUTO)
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
     * 封面图片
     */
        private String image;
    /**
     * 作者ID
     */
    @TableField("user_id")
        private Integer userId;
    /**
     * 状态: 0 : 显示， 2: 隐藏，3：删除
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
