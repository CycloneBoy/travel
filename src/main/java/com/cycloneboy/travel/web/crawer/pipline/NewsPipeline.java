package com.cycloneboy.bookstore.web.crawer.pipline;

import com.cycloneboy.bookstore.entity.News;
import com.cycloneboy.bookstore.mapper.NewsDao;
import com.cycloneboy.bookstore.web.crawer.postgraduate.ResearchNewsSpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;

/**
 * @program: bookstore
 * @description:
 * @author: cycloneboy
 * @create:2018-04-05 20:28
 **/
@Component("NewsPipeline")
public class NewsPipeline implements PageModelPipeline<ResearchNewsSpider> {

    Logger logger = LoggerFactory.getLogger(NewsPipeline.class);


    @Resource
    private NewsDao newsDao;

    @Override
    public void process(ResearchNewsSpider researchNewsSpider, Task task) {

        System.out.println(researchNewsSpider.getId() + " " + researchNewsSpider.getTitle());
        News news = new News( 0
                            ,researchNewsSpider.getTitle()
                            ,researchNewsSpider.getContent()
                            ,researchNewsSpider.getId()
                            ,researchNewsSpider.getDate()
                            ,1,"研招新闻","新闻");
        System.out.println("插入数据: " + news.getNumber() + " --- " +  news.getTitle());

        try {

            newsDao.insertAllColumn(news);

            System.out.println("插入数据成功: " +news.getTitle());
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("插入数据失败");
        }

    }
}
