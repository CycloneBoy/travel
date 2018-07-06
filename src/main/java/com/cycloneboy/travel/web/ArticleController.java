package com.cycloneboy.travel.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.cycloneboy.travel.entity.dto.ExecuteDTO;
import com.cycloneboy.travel.entity.dto.PageQueryDTO;
import com.cycloneboy.travel.entity.dto.PageResultDTO;

import com.cycloneboy.travel.entity.Article;
import com.cycloneboy.travel.service.ArticleService;


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
@RequestMapping("/travel/article")
public class ArticleController extends BaseController{
    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    /**
     * 获取Article 指定ID的实体
     */
    @ApiOperation(value = "获取指定Article", notes = "获取指定Article", httpMethod = "GET")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Article get(@PathVariable String id){
        logger.info("获取指定Article："+id);
        return articleService.selectById(id);
    }
	
	/**
     * 获取Article 的总数
     */
    @ApiOperation(value = "获取Article 的总数", notes = "获取Article 的总数", httpMethod = "GET")
    @RequestMapping(value = "total", method = RequestMethod.GET)
    public ExecuteDTO gettotal(){
        logger.info("获取Article 的总数");

        EntityWrapper<Article> ew=new EntityWrapper<Article>();
        ew.setEntity(new Article());

        int total = articleService.selectCount(ew);
		
		logger.info("获取Article 的总数：" + total);
		
        return new ExecuteDTO(true,"查询总数成功",total);
    }
	

    /**
     * 保存Article
     */
    @ApiOperation(value = "保存Article", notes = "保存Article", httpMethod = "POST")
    @PostMapping("save")
    public ExecuteDTO save(@RequestBody Article entity){
        Boolean result=articleService.insert(entity);
        logger.info("保存Article："+result);
        if(result==false){
            return new ExecuteDTO(false,"保存失败",entity.getId());
        }
        return new ExecuteDTO(true,"保存成功",entity.getId());
    }

    /**
     * 修改Article
     */
    @ApiOperation(value = "修改Article", notes = "修改Article对象实体", httpMethod = "POST")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ExecuteDTO update(@RequestBody Article entity){
        logger.info("修改Article："+entity);
        Article exitArticle=articleService.selectById(entity.getId());
        if(exitArticle==null){
            return new ExecuteDTO(false,"修改失败",entity.getId());
        }
        Boolean result=articleService.updateById(entity);
        return new ExecuteDTO(true,"修改成功",entity.getId());
    }

    /**
     * 获取Article列表
     */
    @ApiOperation(value = "获取Article列表", notes = "获取Article列表", httpMethod = "POST")
    @PostMapping("loadpage")
    public PageResultDTO loadPage(@RequestBody PageQueryDTO params){
        Page<Article> ArticlePage=new Page<>();
        ArticlePage.setSize(params.getSize());
        ArticlePage.setCurrent(params.getPage());

        EntityWrapper<Article> ew=new EntityWrapper<>();
        if(params.getQuery()!=null){
            ew.setEntity(new Article());
            ew.where("id > 0")
              .like("username",params.getQuery().toString());
            logger.info("获取Article列表: 拼接条件查询: "+ew.getSqlSegment());

        }
		
		ArticlePage=articleService.selectPage(ArticlePage,ew);

        logger.info("获取Article列表：总数"+ArticlePage.getRecords().size());
        return new PageResultDTO((long)ArticlePage.getRecords().size(),ArticlePage.getRecords());
    }

    /**
     * 删除Article
     */
    @ApiOperation(value = "删除Article", notes = "删除用", httpMethod = "GET")
    @GetMapping("remove/{id}")
    public ExecuteDTO remove(@PathVariable Integer id){
        Boolean result=articleService.deleteById(id);
        logger.info("删除Article：Id = "+id+" result: "+result);
        return new ExecuteDTO(true,"删除成功",id);
    }

    /**
     * 其他自定义页面
     */
    @ApiOperation(value = "根据邮箱和密码登录", notes = "根据邮箱和密码登录", httpMethod = "POST")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ExecuteDTO login(@RequestBody Article entity){
        logger.info("Article登录："+entity);
        EntityWrapper<Article> ew=new EntityWrapper<>();
        ew.setEntity(new Article());
        ew.where("id > 0 ");
        //                .eq("email",entity.getEmail())
        //                .eq("password",entity.getPassword())
        //                .eq("user_type", 0);

        logger.info("根据邮箱和密码登录: 拼接条件查询: "+ew.getSqlSegment());

        Article exitArticle=articleService.selectOne(ew);
        if(exitArticle==null){
            return new ExecuteDTO(false,"登录失败：邮箱地址跟签到用户名不匹配，或您不是管理员签到用户",exitArticle);
        }
        return new ExecuteDTO(true,"登录成功",exitArticle);
    }
}

