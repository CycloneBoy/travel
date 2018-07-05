package com.cycloneboy.travel.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.cycloneboy.travel.entity.dto.ExecuteDTO;
import com.cycloneboy.travel.entity.dto.PageQueryDTO;
import com.cycloneboy.travel.entity.dto.PageResultDTO;

import com.cycloneboy.travel.entity.Bookcategory;
import com.cycloneboy.travel.service.BookcategoryService;


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
 * @since 2018-03-24
 */
@RestController
@RequestMapping("/travel/bookcategory")
public class BookcategoryController {
    Logger logger = LoggerFactory.getLogger(BookcategoryController.class);

    @Autowired
    private BookcategoryService bookcategoryService;

    /**
     * 获取Bookcategory 指定ID的实体
     */
    @ApiOperation(value = "获取指定Bookcategory", notes = "获取指定Bookcategory", httpMethod = "GET")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Bookcategory get(@PathVariable String id){
        logger.info("获取指定Bookcategory："+id);
        return bookcategoryService.selectById(id);
    }

    /**
     * 保存Bookcategory
     */
    @ApiOperation(value = "保存Bookcategory", notes = "保存Bookcategory", httpMethod = "POST")
    @PostMapping("save")
    public ExecuteDTO save(@RequestBody Bookcategory entity){
        Boolean result=bookcategoryService.insert(entity);
        logger.info("保存Bookcategory："+result);
        if(result==false){
            return new ExecuteDTO(false,"保存失败",entity.getId());
        }
        return new ExecuteDTO(true,"保存成功",entity.getId());
    }

    /**
     * 修改Bookcategory
     */
    @ApiOperation(value = "修改Bookcategory", notes = "修改Bookcategory对象实体", httpMethod = "POST")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ExecuteDTO update(@RequestBody Bookcategory entity){
        logger.info("修改Bookcategory："+entity);
        Bookcategory exitBookcategory=bookcategoryService.selectById(entity.getId());
        if(exitBookcategory==null){
            return new ExecuteDTO(false,"修改失败",entity.getId());
        }
        Boolean result=bookcategoryService.updateById(entity);
        return new ExecuteDTO(true,"修改成功",entity.getId());
    }

    /**
     * 获取Bookcategory列表
     */
    @ApiOperation(value = "获取Bookcategory列表", notes = "获取Bookcategory列表", httpMethod = "POST")
    @PostMapping("loadpage")
    public PageResultDTO loadPage(@RequestBody PageQueryDTO params){
        Page<Bookcategory> BookcategoryPage=new Page<>();
        BookcategoryPage.setSize(params.getSize());
        BookcategoryPage.setCurrent(params.getPage());

        EntityWrapper<Bookcategory> ew=new EntityWrapper<>();
        if(params.getQuery()!=null){
            ew.setEntity(new Bookcategory());
            ew.where("id > 0")
              .like("username",params.getQuery().toString());
            logger.info("获取Bookcategory列表: 拼接条件查询: "+ew.getSqlSegment());

        }
        BookcategoryPage=bookcategoryService.selectPage(BookcategoryPage,ew);


        logger.info("获取Bookcategory列表：总数"+BookcategoryPage.getRecords().size());
        return new PageResultDTO((long)BookcategoryPage.getRecords().size(),BookcategoryPage.getRecords());
    }

    /**
     * 删除Bookcategory
     */
    @ApiOperation(value = "删除Bookcategory", notes = "删除用", httpMethod = "GET")
    @GetMapping("remove/{id}")
    public ExecuteDTO remove(@PathVariable Integer id){
        Boolean result=bookcategoryService.deleteById(id);
        logger.info("删除Bookcategory：Id = "+id+" result: "+result);
        return new ExecuteDTO(true,"删除成功",id);
    }

    /**
     * 其他自定义页面
     */
    @ApiOperation(value = "根据邮箱和密码登录", notes = "根据邮箱和密码登录", httpMethod = "POST")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ExecuteDTO login(@RequestBody Bookcategory entity){
        logger.info("Bookcategory登录："+entity);
        EntityWrapper<Bookcategory> ew=new EntityWrapper<>();
        ew.setEntity(new Bookcategory());
        ew.where("id > 0 ");
        //                .eq("email",entity.getEmail())
        //                .eq("password",entity.getPassword())
        //                .eq("user_type", 0);

        logger.info("根据邮箱和密码登录: 拼接条件查询: "+ew.getSqlSegment());

        Bookcategory exitBookcategory=bookcategoryService.selectOne(ew);
        if(exitBookcategory==null){
            return new ExecuteDTO(false,"登录失败：邮箱地址跟签到用户名不匹配，或您不是管理员签到用户",exitBookcategory);
        }
        return new ExecuteDTO(true,"登录成功",exitBookcategory);
    }
}

