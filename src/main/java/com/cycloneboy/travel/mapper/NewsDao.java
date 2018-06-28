package com.cycloneboy.bookstore.mapper;

import com.cycloneboy.bookstore.entity.News;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cycloneboy
 * @since 2018-04-05
 */
@Mapper
public interface NewsDao extends BaseMapper<News> {

}
