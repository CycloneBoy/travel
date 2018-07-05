package com.cycloneboy.travel.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.cycloneboy.travel.entity.dto.ExecuteDTO;
import com.cycloneboy.travel.entity.dto.PageQueryDTO;
import com.cycloneboy.travel.entity.dto.PageResultDTO;

import com.cycloneboy.travel.entity.Category;
import com.cycloneboy.travel.service.CategoryService;


import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器 RESTful API
 * </p>
 *
 * @author cycloneboy
 * @since 2018-03-24
 */
@RestController
@RequestMapping("/travel/category")
public class CategoryController extends BaseController{
    Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取Category 指定ID的实体
     */
    @ApiOperation(value = "获取指定Category", notes = "获取指定Category", httpMethod = "GET")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Category get(@PathVariable String id){
        logger.info("获取指定Category："+id);
        return categoryService.selectById(id);
    }

    /**
     * 保存Category
     */
    @ApiOperation(value = "保存Category", notes = "保存Category", httpMethod = "POST")
    @PostMapping("save")
    public ExecuteDTO save(@RequestBody Category entity){
        Boolean result=categoryService.insert(entity);
        logger.info("保存Category："+result);
        if(result==false){

            return new ExecuteDTO(false,"保存失败",entity.getId());
        }
        return new ExecuteDTO(true,"保存成功",entity.getId());
    }

    /**
     * 修改Category
     */
    @ApiOperation(value = "修改Category", notes = "修改Category对象实体", httpMethod = "POST")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ExecuteDTO update(@RequestBody Category entity){
        logger.info("修改Category："+entity);
        Category exitCategory=categoryService.selectById(entity.getId());
        if(exitCategory==null){
            return new ExecuteDTO(false,"修改失败",entity.getId());
        }
        Boolean result=categoryService.updateById(entity);
        return new ExecuteDTO(true,"修改成功",entity.getId());
    }

    /**
     * 获取Category列表
     */
    @ApiOperation(value = "获取Category列表", notes = "获取Category列表", httpMethod = "POST")
    @PostMapping("loadpage")
    public PageResultDTO loadPage(@RequestBody PageQueryDTO params){

        EntityWrapper<Category> ew=new EntityWrapper<Category>();
        Page<Category> CategoryPage=new Page<>(params.getPage(),params.getSize(),
                        "id",true);

        if(params.getQuery()!=null){
            ew.setEntity(new Category());
            ew.where("id > 0")
              .like("username",params.getQuery().toString());
            logger.info("获取Category列表: 拼接条件查询: "+ew.getSqlSegment());

        }
        CategoryPage=categoryService.selectPage(CategoryPage,ew);

        logger.info("获取Category列表：总数"+CategoryPage.getRecords().size());
        return new PageResultDTO((long)CategoryPage.getRecords().size(),CategoryPage.getRecords());
    }

    /**
     * 删除Category
     */
    @ApiOperation(value = "删除Category", notes = "删除用", httpMethod = "GET")
    @GetMapping("remove/{id}")
    public ExecuteDTO remove(@PathVariable Integer id){
        Boolean result=categoryService.deleteById(id);
        logger.info("删除Category：Id = "+id+" result: "+result);
        return new ExecuteDTO(true,"删除成功",id);
    }

    /**
     * 其他自定义页面
     */
    @ApiOperation(value = "根据邮箱和密码登录", notes = "根据邮箱和密码登录", httpMethod = "POST")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ExecuteDTO login(@RequestBody Category entity){
        logger.info("Category登录："+entity);
        EntityWrapper<Category> ew=new EntityWrapper<>();
        ew.setEntity(new Category());
        ew.where("id > 0 ");
        //                .eq("email",entity.getEmail())
        //                .eq("password",entity.getPassword())
        //                .eq("user_type", 0);

        logger.info("根据邮箱和密码登录: 拼接条件查询: "+ew.getSqlSegment());

        Category exitCategory=categoryService.selectOne(ew);
        if(exitCategory==null){
            return new ExecuteDTO(false,"登录失败：邮箱地址跟签到用户名不匹配，或您不是管理员签到用户",exitCategory);
        }
        return new ExecuteDTO(true,"登录成功",exitCategory);
    }

    /**
     * 获取所有Category列表
     */
    @ApiOperation(value = "获取所有Category列表", notes = "获取所有Category列表", httpMethod = "GET")
    @GetMapping("list")
    public PageResultDTO listAll(){
        EntityWrapper<Category> ew=new EntityWrapper<Category>();
        ew.setEntity(new Category());
        ew.where("id > 0");
        logger.info("获取所有Category列表: 拼接条件查询: "+ew.getSqlSegment());

        List<Category> categoryList =  categoryService.selectList(ew);
        logger.info("获取所有Category列表总数:"+categoryList.size());
        return new PageResultDTO((long)categoryList.size(),categoryList);
    }
}

