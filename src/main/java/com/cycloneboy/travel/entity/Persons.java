package com.cycloneboy.travel.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
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
@TableName("persons")
public class Persons extends Model<Persons> {

private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名称
     */
        private String username;
    /**
     * 用户密码
     */
        private String password;
    /**
     * 用户性别：0: 男生 1：女生, 3:不详
     */
        private Integer gender;
    /**
     * 用户邮箱
     */
        private String email;
    /**
     * 用户电话
     */
        private String phone;
    /**
     * 用户类型：0：管理员 1：用户 2：其他
     */
    @TableField("user_type")
        private Integer userType;
    /**
     * 用户地址
     */
        private String address;
    /**
     * 创建时间
     */
    @TableField("create_datetime")
        private Date createDatetime;


    @Override
    protected Serializable pkVal() {
            return this.id;
        }
}
