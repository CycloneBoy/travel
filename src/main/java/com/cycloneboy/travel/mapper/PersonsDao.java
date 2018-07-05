package com.cycloneboy.travel.mapper;

import com.cycloneboy.travel.entity.Persons;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cycloneboy
 * @since 2018-03-15
 */
@Mapper
public interface PersonsDao extends BaseMapper<Persons> {

}
