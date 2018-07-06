package com.cycloneboy.travel.service.impl;

import com.cycloneboy.travel.entity.Comment;
import com.cycloneboy.travel.mapper.CommentDao;
import com.cycloneboy.travel.service.CommentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cycloneboy
 * @since 2018-07-05
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements CommentService {

}
