package com.cycloneboy.travel.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.cycloneboy.travel.entity.dto.ExecuteDTO;
import com.cycloneboy.travel.entity.dto.PageQueryDTO;
import com.cycloneboy.travel.entity.dto.PageResultDTO;

import com.cycloneboy.travel.entity.Persons;
import com.cycloneboy.travel.service.PersonsService;


import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
@RequestMapping("/travel/persons")
public class PersonsController extends BaseController{
    Logger logger = LoggerFactory.getLogger(PersonsController.class);

    @Autowired
    private PersonsService personsService;

    /**
     * 获取Persons 指定ID的实体
     */
    @ApiOperation(value = "获取指定Persons", notes = "获取指定Persons", httpMethod = "GET")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Persons get(@PathVariable String id){
        logger.info("获取指定Persons："+id);
        return personsService.selectById(id);
    }

    /**
     * 保存Persons
     */
    @ApiOperation(value = "保存Persons", notes = "保存Persons", httpMethod = "POST")
    @PostMapping("save")
    public ExecuteDTO save(@RequestBody Persons entity){
        Boolean result = false;
        try {
            result = personsService.insert(entity);
        }catch (DuplicateKeyException e){
            return new ExecuteDTO(false,"保存失败,邮箱已注册",entity.getEmail());
        }
        logger.info("保存Persons："+result);
        if(result==false){
            return new ExecuteDTO(false,"保存失败",entity.getId());
        }
        return new ExecuteDTO(true,"保存成功",entity.getId());
    }

    /**
     * 修改Persons
     */
    @ApiOperation(value = "修改Persons", notes = "修改Persons对象实体", httpMethod = "POST")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ExecuteDTO update(@RequestBody Persons entity){
        logger.info("修改Persons："+entity);
        Persons exitPersons=personsService.selectById(entity.getId());
        if(exitPersons==null){
            return new ExecuteDTO(false,"修改失败",entity.getId());
        }
        Boolean result=personsService.updateById(entity);
        return new ExecuteDTO(true,"修改成功",entity.getId());
    }

    /**
     * 获取Persons列表
     */
    @ApiOperation(value = "获取Persons列表", notes = "获取Persons列表", httpMethod = "POST")
    @PostMapping("loadpage")
    public PageResultDTO loadPage(@RequestBody PageQueryDTO params){
        Page<Persons> PersonsPage=new Page<>();
        PersonsPage.setSize(params.getSize());
        PersonsPage.setCurrent(params.getPage());

        EntityWrapper<Persons> ew=new EntityWrapper<>();
        if(params.getQuery()!=null) {
            ew.setEntity(new Persons());
            ew.where("id > 0")
                    .like("username", params.getQuery().toString());
            logger.info("获取Persons列表: 拼接条件查询: " + ew.getSqlSegment());
        }

        PersonsPage=personsService.selectPage(PersonsPage,ew);


        logger.info("获取Persons列表：总数"+PersonsPage.getRecords().size());
        return new PageResultDTO((long)PersonsPage.getRecords().size(),PersonsPage.getRecords());
    }

    /**
     * 删除Persons
     */
    @ApiOperation(value = "删除Persons", notes = "删除用", httpMethod = "GET")
    @GetMapping("remove/{id}")
    public ExecuteDTO remove(@PathVariable Integer id){
        Boolean result=personsService.deleteById(id);
        logger.info("删除Persons：Id = "+id+" result: "+result);
        return new ExecuteDTO(true,"删除成功",id);
    }

    /**
     * 其他自定义页面
     */
    @ApiOperation(value = "根据邮箱和密码登录", notes = "根据邮箱和密码登录", httpMethod = "POST")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ExecuteDTO login(@RequestBody Persons entity){
        logger.info("Persons登录："+entity);
        EntityWrapper<Persons> ew=new EntityWrapper<>();
        ew.setEntity(new Persons());
        ew.where("id > 0 ")
                        .eq("email",entity.getEmail())
                        .eq("password",entity.getPassword());
//                        .eq("user_type", 0);

        logger.info("根据邮箱和密码登录: 拼接条件查询: "+ew.getSqlSegment());

        Persons exitPersons=personsService.selectOne(ew);
        if(exitPersons==null){
            return new ExecuteDTO(false,"登录失败：邮箱地址跟用户名不匹配，或您不是管理员用户",exitPersons);
        }
        return new ExecuteDTO(true,"登录成功",exitPersons);
    }
}

