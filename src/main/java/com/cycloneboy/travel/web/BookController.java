package com.cycloneboy.travel.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.cycloneboy.travel.entity.dto.ExecuteDTO;
import com.cycloneboy.travel.entity.dto.PageQueryDTO;
import com.cycloneboy.travel.entity.dto.PageResultDTO;

import com.cycloneboy.travel.entity.Book;
import com.cycloneboy.travel.service.BookService;


import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  前端控制器 RESTful API
 * </p>
 *
 * @author cycloneboy
 * @since 2018-03-24
 */
@RestController
@RequestMapping("/travel/book")
public class BookController extends BaseController{
    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    /**
     * 获取Book 指定ID的实体
     */
    @ApiOperation(value = "获取指定Book", notes = "获取指定Book", httpMethod = "GET")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Book get(@PathVariable String id){
        logger.info("获  取指定Book："+id);
        return bookService.selectById(id);
    }

    /**
     * 保存Book
     */
    @ApiOperation(value = "保存Book", notes = "保存Book", httpMethod = "POST")
    @PostMapping("save")
    public ExecuteDTO save(@RequestBody Book entity){
        Boolean result=bookService.insert(entity);
        logger.info("保存Book："+result + " " + entity.getId() );

        if(result==false){
            return new ExecuteDTO(false,"保存失败",entity.getId());
        }

        return new ExecuteDTO(true,"保存成功",entity.getId());
    }

    /**
     * 修改Book
     */
    @ApiOperation(value = "修改Book", notes = "修改Book对象实体", httpMethod = "POST")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ExecuteDTO update(@RequestBody Book entity){
        logger.info("修改Book："+entity);
        Book exitBook=bookService.selectById(entity.getId());
        if(exitBook==null){
            return new ExecuteDTO(false,"修改失败",entity.getId());
        }
        Boolean result=bookService.updateById(entity);
        return new ExecuteDTO(true,"修改成功",entity.getId());
    }

    /**
     * 获取Book列表
     */
    @ApiOperation(value = "获取Book列表", notes = "获取Book列表", httpMethod = "POST")
    @PostMapping("loadpage")
    public PageResultDTO loadPage(@RequestBody PageQueryDTO params){
        Page<Book> BookPage=new Page<>();
        BookPage.setSize(params.getSize());
        BookPage.setCurrent(params.getPage());

        EntityWrapper<Book> ew=new EntityWrapper<>();
        if(params.getQuery()!=null){
            ew.setEntity(new Book());
            ew.where("id > 0")
              .like("username",params.getQuery().toString());
            logger.info("获取Book列表: 拼接条件查询: "+ew.getSqlSegment());

        }

        BookPage=bookService.selectPage(BookPage,ew);

        logger.info("获取Book列表：总数"+BookPage.getRecords().size());
        return new PageResultDTO((long)BookPage.getRecords().size(),BookPage.getRecords());
    }

    /**
     * 删除Book
     */
    @ApiOperation(value = "删除Book", notes = "删除用", httpMethod = "GET")
    @GetMapping("remove/{id}")
    public ExecuteDTO remove(@PathVariable Integer id){
        Boolean result=bookService.deleteById(id);
        logger.info("删除Book：Id = "+id+" result: "+result);
        return new ExecuteDTO(true,"删除成功",id);
    }

    /**
     * 其他自定义页面
     */
    @ApiOperation(value = "根据邮箱和密码登录", notes = "根据邮箱和密码登录", httpMethod = "POST")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ExecuteDTO login(@RequestBody Book entity){
        logger.info("Book登录："+entity);
        EntityWrapper<Book> ew=new EntityWrapper<>();
        ew.setEntity(new Book());
        ew.where("id > 0 ");
        //                .eq("email",entity.getEmail())
        //                .eq("password",entity.getPassword())
        //                .eq("user_type", 0);

        logger.info("根据邮箱和密码登录: 拼接条件查询: "+ew.getSqlSegment());

        Book exitBook=bookService.selectOne(ew);
        if(exitBook==null){
            return new ExecuteDTO(false,"登录失败：邮箱地址跟签到用户名不匹配，或您不是管理员签到用户",exitBook);
        }
        return new ExecuteDTO(true,"登录成功",exitBook);
    }

    /**
     * 实现文件上传
     * */
    @ApiOperation(value = "实现文件上传", notes = "实现文件上传", httpMethod = "POST")
    @RequestMapping(value = "fileupload",method = RequestMethod.POST)
    public ExecuteDTO fileUpload(@RequestBody MultipartFile file){
        logger.info("开始上传文件：");
        if(file == null){
            return new ExecuteDTO(false,"文件上传失败：请选择文件","error");
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        String filePath =  new SimpleDateFormat("yyyyMMddHHmmss").
                format(new Date())  + "_" + fileName ;
        logger.info("文件上传：" + fileName + "-->" + size + " --> " + filePath);

        String fileType = fileName.substring(fileName.indexOf('.')).toLowerCase();

        String path = "D:/web/nodeJs/travel/static/upload/cover" ;
        if((fileType != "png") && (fileType != "jpg")  && (fileType != "jpeg") && (fileType != "bmp")) {
            path = "D:/web/nodeJs/travel/static/upload/file" ;
        }

        logger.info("文件上传存储路径：" + path + "/" + filePath + "-->" + size + " --> " + fileType);

        File dest = new File(path + "/" + filePath);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return new ExecuteDTO(true, "文件上传成功", path + "/" + filePath);
        }catch (IOException e) {
            e.printStackTrace();
            return new ExecuteDTO(false,"文件上传失败：请重新上传",e.getMessage());
        }
    }
}

