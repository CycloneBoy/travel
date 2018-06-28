package com.cycloneboy.bookstore.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.cycloneboy.bookstore.entity.dto.ExecuteDTO;
import com.cycloneboy.bookstore.entity.dto.PageQueryDTO;
import com.cycloneboy.bookstore.entity.dto.PageResultDTO;

import com.cycloneboy.bookstore.entity.News;
import com.cycloneboy.bookstore.service.NewsService;


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
 * @since 2018-04-05
 */
@RestController
@RequestMapping("/bookstore/news")
public class NewsController {
    Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;

    /**
     * 获取News 指定ID的实体
     */
    @ApiOperation(value = "获取指定News", notes = "获取指定News", httpMethod = "GET")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public News get(@PathVariable String id){
        logger.info("获取指定News："+id);
        return newsService.selectById(id);
    }

    /**
     * 获取News 的总数
     */
    @ApiOperation(value = "获取News总数", notes = "获取News总数", httpMethod = "GET")
    @RequestMapping(value = "total", method = RequestMethod.GET)
    public ExecuteDTO gettotal(){

        EntityWrapper<News> ew=new EntityWrapper<News>();
        ew.setEntity(new News());
        ew.where("id > 0 ");

        int total = newsService.selectCount(ew);

        logger.info("获取News总数：" + total);

        return new ExecuteDTO(true,"查询总数成功",total);
    }

    /**
     * 保存News
     */
    @ApiOperation(value = "保存News", notes = "保存News", httpMethod = "POST")
    @PostMapping("save")
    public ExecuteDTO save(@RequestBody News entity){
        Boolean result=newsService.insert(entity);
        logger.info("保存News："+result);
        if(result==false){
            return new ExecuteDTO(false,"保存失败",entity.getId());
        }
        return new ExecuteDTO(true,"保存成功",entity.getId());
    }

    /**
     * 修改News
     */
    @ApiOperation(value = "修改News", notes = "修改News对象实体", httpMethod = "POST")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ExecuteDTO update(@RequestBody News entity){
        logger.info("修改News："+entity);
        News exitNews=newsService.selectById(entity.getId());
        if(exitNews==null){
            return new ExecuteDTO(false,"修改失败",entity.getId());
        }
        Boolean result=newsService.updateById(entity);
        return new ExecuteDTO(true,"修改成功",entity.getId());
    }

    /**
     * 获取News列表
     */
    @ApiOperation(value = "获取News列表", notes = "获取News列表", httpMethod = "POST")
    @PostMapping("loadpage")
    public PageResultDTO loadPage(@RequestBody PageQueryDTO params){
        Page<News> NewsPage=new Page<>();
        NewsPage.setSize(params.getSize());
        NewsPage.setCurrent(params.getPage());

        EntityWrapper<News> ew=new EntityWrapper<News>();
        if(params.getQuery()!=null){
            ew.setEntity(new News());
            ew.where("id > 0")
              .like("title",params.getQuery().toString());
            logger.info("获取News列表: 拼接条件查询: "+ew.getSqlSegment());

        }

        NewsPage=newsService.selectPage(NewsPage,ew);

        int total = newsService.selectCount(ew);

        logger.info("获取News总数：" + total);

        logger.info("获取News列表：总数"+NewsPage.getRecords().size());
        return new PageResultDTO((long)NewsPage.getRecords().size(),NewsPage.getRecords());
    }

    /**
     * 删除News
     */
    @ApiOperation(value = "删除News", notes = "删除用", httpMethod = "GET")
    @GetMapping("remove/{id}")
    public ExecuteDTO remove(@PathVariable Integer id){
        Boolean result=newsService.deleteById(id);
        logger.info("删除News：Id = "+id+" result: "+result);
        return new ExecuteDTO(true,"删除成功",id);
    }

    /**
     * 其他自定义页面
     */
    @ApiOperation(value = "根据邮箱和密码登录", notes = "根据邮箱和密码登录", httpMethod = "POST")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ExecuteDTO login(@RequestBody News entity){
        logger.info("News登录："+entity);
        EntityWrapper<News> ew=new EntityWrapper<>();
        ew.setEntity(new News());
        ew.where("id > 0 ");
        //                .eq("email",entity.getEmail())
        //                .eq("password",entity.getPassword())
        //                .eq("user_type", 0);

        logger.info("根据邮箱和密码登录: 拼接条件查询: "+ew.getSqlSegment());

        News exitNews=newsService.selectOne(ew);
        if(exitNews==null){
            return new ExecuteDTO(false,"登录失败：邮箱地址跟签到用户名不匹配，或您不是管理员签到用户",exitNews);
        }
        return new ExecuteDTO(true,"登录成功",exitNews);
    }
}

