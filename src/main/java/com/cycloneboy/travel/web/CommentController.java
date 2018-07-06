package com.cycloneboy.travel.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.cycloneboy.travel.entity.dto.ExecuteDTO;
import com.cycloneboy.travel.entity.dto.PageQueryDTO;
import com.cycloneboy.travel.entity.dto.PageResultDTO;

import com.cycloneboy.travel.entity.Comment;
import com.cycloneboy.travel.service.CommentService;


import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器 RESTful API
 * </p>
 *
 * @author cycloneboy
 * @since 2018-07-05
 */
@RestController
@RequestMapping("/travel/comment")
public class CommentController extends BaseController{
    Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    /**
     * 获取Comment 指定ID的实体
     */
    @ApiOperation(value = "获取指定Comment", notes = "获取指定Comment", httpMethod = "GET")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Comment get(@PathVariable String id){
        logger.info("获取指定Comment："+id);
        return commentService.selectById(id);
    }
	
	/**
     * 获取Comment 的总数
     */
    @ApiOperation(value = "获取Comment 的总数", notes = "获取Comment 的总数", httpMethod = "GET")
    @RequestMapping(value = "total", method = RequestMethod.GET)
    public ExecuteDTO gettotal(){
        logger.info("获取Comment 的总数");

        EntityWrapper<Comment> ew=new EntityWrapper<Comment>();
        ew.setEntity(new Comment());

        int total = commentService.selectCount(ew);
		
		logger.info("获取Comment 的总数：" + total);
		
        return new ExecuteDTO(true,"查询总数成功",total);
    }
	

    /**
     * 保存Comment
     */
    @ApiOperation(value = "保存Comment", notes = "保存Comment", httpMethod = "POST")
    @PostMapping("save")
    public ExecuteDTO save(@RequestBody Comment entity){
        Boolean result=commentService.insert(entity);
        logger.info("保存Comment："+result);
        if(result==false){
            return new ExecuteDTO(false,"保存失败",entity.getId());
        }
        return new ExecuteDTO(true,"保存成功",entity.getId());
    }

    /**
     * 修改Comment
     */
    @ApiOperation(value = "修改Comment", notes = "修改Comment对象实体", httpMethod = "POST")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ExecuteDTO update(@RequestBody Comment entity){
        logger.info("修改Comment："+entity);
        Comment exitComment=commentService.selectById(entity.getId());
        if(exitComment==null){
            return new ExecuteDTO(false,"修改失败",entity.getId());
        }
        Boolean result=commentService.updateById(entity);
        return new ExecuteDTO(true,"修改成功",entity.getId());
    }

    /**
     * 获取Comment列表
     */
    @ApiOperation(value = "获取Comment列表", notes = "获取Comment列表", httpMethod = "POST")
    @PostMapping("loadpage")
    public PageResultDTO loadPage(@RequestBody PageQueryDTO params){
        Page<Comment> CommentPage=new Page<>();
        CommentPage.setSize(params.getSize());
        CommentPage.setCurrent(params.getPage());

        EntityWrapper<Comment> ew=new EntityWrapper<>();
        if(params.getQuery()!=null){
            ew.setEntity(new Comment());
            ew.where("id > 0")
              .like("username",params.getQuery().toString());
            logger.info("获取Comment列表: 拼接条件查询: "+ew.getSqlSegment());

        }
		
		CommentPage=commentService.selectPage(CommentPage,ew);

        logger.info("获取Comment列表：总数"+CommentPage.getRecords().size());
        return new PageResultDTO((long)CommentPage.getRecords().size(),CommentPage.getRecords());
    }

    /**
     * 删除Comment
     */
    @ApiOperation(value = "删除Comment", notes = "删除用", httpMethod = "GET")
    @GetMapping("remove/{id}")
    public ExecuteDTO remove(@PathVariable Integer id){
        Boolean result=commentService.deleteById(id);
        logger.info("删除Comment：Id = "+id+" result: "+result);
        return new ExecuteDTO(true,"删除成功",id);
    }

    /**
     * 其他自定义页面
     */
    @ApiOperation(value = "根据邮箱和密码登录", notes = "根据邮箱和密码登录", httpMethod = "POST")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ExecuteDTO login(@RequestBody Comment entity){
        logger.info("Comment登录："+entity);
        EntityWrapper<Comment> ew=new EntityWrapper<>();
        ew.setEntity(new Comment());
        ew.where("id > 0 ");
        //                .eq("email",entity.getEmail())
        //                .eq("password",entity.getPassword())
        //                .eq("user_type", 0);

        logger.info("根据邮箱和密码登录: 拼接条件查询: "+ew.getSqlSegment());

        Comment exitComment=commentService.selectOne(ew);
        if(exitComment==null){
            return new ExecuteDTO(false,"登录失败：邮箱地址跟签到用户名不匹配，或您不是管理员签到用户",exitComment);
        }
        return new ExecuteDTO(true,"登录成功",exitComment);
    }
}

