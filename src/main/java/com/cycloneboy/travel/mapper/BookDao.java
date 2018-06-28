package com.cycloneboy.bookstore.mapper;

import com.cycloneboy.bookstore.entity.Book;
import com.baomidou.mybatisplus.mapper.BaseMapper;
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
