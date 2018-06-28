package com.cycloneboy.bookstore.web.crawer.pipline;

import com.cycloneboy.bookstore.entity.PubmedNews;
import com.cycloneboy.bookstore.service.PubmedNewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;
import java.util.Map;

/**
 * @program: bookstore
 * @description:
 * @author: cycloneboy
 * @create:2018-04-05 19:03
 **/
@Repository
public class PubmedNewsPipeline implements Pipeline{


    Logger logger = LoggerFactory.getLogger(PubmedNewsElasticsearchPipeline.class);

    @Autowired
    private PubmedNewsService pubmedNewsService ;

    @Override
    public void process(ResultItems resultItems, Task task) {
        PubmedNews pubmedNews = new PubmedNews();


//        System.out.println("get page: " + resultItems.getRequest().getUrl());
        //遍历所有结果，输出到控制台，上面例子中的"author"、"name"、"readme"都是一个key，其结果则是对应的value
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
//            System.out.println(entry.getKey() + ":\t" + entry.getValue());

            System.out.println(entry.getKey() + " <-> " + entry.getValue());

            System.out.println(" 比较 ID ：" + entry.getKey().equals("id"));

            if(entry.getKey().equals("id")){
                pubmedNews.setId(entry.getValue().toString());
            }else if(entry.getKey().equals("title")){
                pubmedNews.setTitle(entry.getValue().toString());
            }else if(entry.getKey().equals("content")){
                pubmedNews.setContent(entry.getValue().toString());
            }else if(entry.getKey().equals("date")){
                pubmedNews.setDate((Date) entry.getValue());
            }else {
                System.out.println("比较出错");
            }
        }


        System.out.println("保存一条数据: " + pubmedNews.getTitle());
        System.out.println(pubmedNews.toString());


        System.out.println(pubmedNewsService);

//        pubmedNewsService.save(pubmedNews);
    }

}
