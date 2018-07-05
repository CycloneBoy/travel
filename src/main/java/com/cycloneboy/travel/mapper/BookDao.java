package com.cycloneboy.travel.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cycloneboy.travel.entity.Book;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cycloneboy
 * @since 2018-03-16
 */
@Mapper
public interface BookDao extends BaseMapper<Book> {

}
