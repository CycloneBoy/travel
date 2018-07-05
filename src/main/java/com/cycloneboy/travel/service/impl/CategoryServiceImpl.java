package com.cycloneboy.travel.service.impl;

import com.cycloneboy.travel.entity.Category;
import com.cycloneboy.travel.mapper.CategoryDao;
import com.cycloneboy.travel.service.CategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cycloneboy
 * @since 2018-03-24
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {

}
