package com.cycloneboy.bookstore.web.crawer.pipline;

import com.cycloneboy.bookstore.entity.Article;
import com.cycloneboy.bookstore.entity.PubmedNews;
import com.cycloneboy.bookstore.service.PubmedNewsService;
import com.cycloneboy.bookstore.service.impl.PubmedNewsServiceImpl;
import com.cycloneboy.bookstore.web.crawer.postgraduate.ResearchNewsSpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @program: bookstore
 * @description:
 * @author: cycloneboy
 * @create:2018-04-04 11:03
 **/
@Component("PubmedNewsElasticsearchPipeline")
public class PubmedNewsElasticsearchPipeline implements PageModelPipeline<ResearchNewsSpider> {

    Logger logger = LoggerFactory.getLogger(PubmedNewsElasticsearchPipeline.class);

    @Resource
    private PubmedNewsService pubmedNewsService ;


    @Override
    public void process(ResearchNewsSpider researchNewsSpider, Task task) {

        PubmedNews pubmedNews = new PubmedNews();

        System.out.println(researchNewsSpider.getTitle());

        if(researchNewsSpider != null && !researchNewsSpider.getTitle().toString().isEmpty()){

            pubmedNews.setId(researchNewsSpider.getId());
            pubmedNews.setTitle(researchNewsSpider.getTitle());
            pubmedNews.setContent(researchNewsSpider.getContent());
            pubmedNews.setDate(researchNewsSpider.getDate());

            System.out.println("保存一条数据 ：" + pubmedNews.getTitle());




            if(pubmedNews != null && pubmedNewsService != null){
                pubmedNewsService.save(pubmedNews);
                System.out.println("保存数据-------成功-------->");

            }else{

                System.out.println(pubmedNewsService);

                System.out.println("保存数据-------失败-------->");
            }

        }

    }
}
