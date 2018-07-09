package com.cycloneboy.travel.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.cycloneboy.travel.entity.dto.ExecuteDTO;
import com.cycloneboy.travel.entity.dto.PageQueryDTO;
import com.cycloneboy.travel.entity.dto.PageResultDTO;

import com.cycloneboy.travel.entity.Timeline;
import com.cycloneboy.travel.entity.dto.TimelineDTO;
import com.cycloneboy.travel.service.TimelineService;


import io.swagger.annotations.ApiOperation;
import org.apache.http.impl.execchain.TunnelRefusedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器 RESTful API
 * </p>
 *
 * @author cycloneboy
 * @since 2018-07-06
 */
@RestController
@RequestMapping("/travel/timeline")
public class TimelineController {
    Logger logger = LoggerFactory.getLogger(TimelineController.class);

    @Autowired
    private TimelineService timelineService;

    /**
     * 获取Timeline 指定ID的实体
     */
    @ApiOperation(value = "获取指定Timeline", notes = "获取指定Timeline", httpMethod = "GET")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Timeline get(@PathVariable String id){
        logger.info("获取指定Timeline："+id);
        return timelineService.selectById(id);
    }
	
	/**
     * 获取Timeline 的总数
     */
    @ApiOperation(value = "获取Timeline 的总数", notes = "获取Timeline 的总数", httpMethod = "GET")
    @RequestMapping(value = "total", method = RequestMethod.GET)
    public ExecuteDTO gettotal(){
        logger.info("获取Timeline 的总数");

        EntityWrapper<Timeline> ew=new EntityWrapper<Timeline>();
        ew.setEntity(new Timeline());

        int total = timelineService.selectCount(ew);
		
		logger.info("获取Timeline 的总数：" + total);
		
        return new ExecuteDTO(true,"查询总数成功",total);
    }
	

    /**
     * 保存Timeline
     */
    @ApiOperation(value = "保存Timeline", notes = "保存Timeline", httpMethod = "POST")
    @PostMapping("save")
    public ExecuteDTO save(@RequestBody Timeline entity){
        Boolean result=timelineService.insert(entity);
        logger.info("保存Timeline："+result);
        if(result==false){
            return new ExecuteDTO(false,"保存失败",entity.getId());
        }
        return new ExecuteDTO(true,"保存成功",entity.getId());
    }

    /**
     * 修改Timeline
     */
    @ApiOperation(value = "修改Timeline", notes = "修改Timeline对象实体", httpMethod = "POST")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ExecuteDTO update(@RequestBody Timeline entity){
        logger.info("修改Timeline："+entity);
        Timeline exitTimeline=timelineService.selectById(entity.getId());
        if(exitTimeline==null){
            return new ExecuteDTO(false,"修改失败",entity.getId());
        }
        Boolean result=timelineService.updateById(entity);
        return new ExecuteDTO(true,"修改成功",entity.getId());
    }

    /**
     * 获取Timeline列表
     */
    @ApiOperation(value = "获取Timeline列表", notes = "获取Timeline列表", httpMethod = "POST")
    @PostMapping("loadpage")
    public PageResultDTO loadPage(@RequestBody PageQueryDTO params){
        Page<Timeline> TimelinePage=new Page<>();
        TimelinePage.setSize(params.getSize());
        TimelinePage.setCurrent(params.getPage());

        EntityWrapper<Timeline> ew=new EntityWrapper<>();
        if(params.getQuery()!=null){
            ew.setEntity(new Timeline());
            ew.where("id > 0")
              .like("username",params.getQuery().toString());
            logger.info("获取Timeline列表: 拼接条件查询: "+ew.getSqlSegment());

        }
		
		TimelinePage=timelineService.selectPage(TimelinePage,ew);

        logger.info("获取Timeline列表：总数"+TimelinePage.getRecords().size());
        return new PageResultDTO((long)TimelinePage.getRecords().size(),TimelinePage.getRecords());
    }

    /**
     * 删除Timeline
     */
    @ApiOperation(value = "删除Timeline", notes = "删除用", httpMethod = "GET")
    @GetMapping("remove/{id}")
    public ExecuteDTO remove(@PathVariable Integer id){
        Boolean result=timelineService.deleteById(id);
        logger.info("删除Timeline：Id = "+id+" result: "+result);
        return new ExecuteDTO(true,"删除成功",id);
    }

    /**
     * 其他自定义页面
     */
    @ApiOperation(value = "根据邮箱和密码登录", notes = "根据邮箱和密码登录", httpMethod = "POST")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ExecuteDTO login(@RequestBody Timeline entity){
        logger.info("Timeline登录："+entity);
        EntityWrapper<Timeline> ew=new EntityWrapper<>();
        ew.setEntity(new Timeline());
        ew.where("id > 0 ");
        //                .eq("email",entity.getEmail())
        //                .eq("password",entity.getPassword())
        //                .eq("user_type", 0);

        logger.info("根据邮箱和密码登录: 拼接条件查询: "+ew.getSqlSegment());

        Timeline exitTimeline=timelineService.selectOne(ew);
        if(exitTimeline==null){
            return new ExecuteDTO(false,"登录失败：邮箱地址跟签到用户名不匹配，或您不是管理员签到用户",exitTimeline);
        }
        return new ExecuteDTO(true,"登录成功",exitTimeline);
    }

    /**
     * 获取登录用户的Timeline列表
     */
    @ApiOperation(value = "获取登录用户的Timeline列表", notes = "获取登录用户的Timeline列表", httpMethod = "POST")
    @PostMapping("loadUserTimeline")
    public PageResultDTO getTimelineByUserId(@RequestBody PageQueryDTO params){

        Page<Timeline> TimelinePage=new Page<>();
        TimelinePage.setSize(params.getSize());
        TimelinePage.setCurrent(params.getPage());

        EntityWrapper<Timeline> ew = new EntityWrapper<>();
        if(params.getQuery()!=null){
            ew.setEntity(new Timeline());
            ew.where("user_id = " + params.getQuery().toString() )
              .orderBy("create_time", false);
            logger.info("获取Timeline列表: 拼接条件查询: "+ew.getSqlSegment());

        }

        TimelinePage=timelineService.selectPage(TimelinePage,ew);

        logger.info("获取登录用户的Timeline列表：总数"+TimelinePage.getRecords().size());

        List<TimelineDTO> timelineDTOList = new ArrayList<>();
        for (Timeline timeline :
                TimelinePage.getRecords()) {
           timelineDTOList.add(new TimelineDTO(timeline));
        }

        return new PageResultDTO((long)TimelinePage.getRecords().size(),timelineDTOList);
    }
}

