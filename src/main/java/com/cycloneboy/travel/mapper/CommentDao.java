package com.cycloneboy.travel.mapper;

import com.cycloneboy.travel.entity.Comment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cycloneboy
 * @since 2018-07-05
 */
@Mapper
public interface CommentDao extends BaseMapper<Comment> {

}
