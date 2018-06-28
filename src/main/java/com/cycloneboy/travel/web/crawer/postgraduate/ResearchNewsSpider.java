package com.cycloneboy.bookstore.web.crawer.postgraduate;

import com.cycloneboy.bookstore.entity.PubmedNews;
import com.cycloneboy.bookstore.service.PubmedNewsService;
import com.cycloneboy.bookstore.util.constant.ConstanCrawer;
import com.cycloneboy.bookstore.web.BookController;
import com.cycloneboy.bookstore.web.crawer.pipline.PubmedNewsElasticsearchPipeline;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.Formatter;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

import java.util.Date;

/**
 * @program: bookstore
 * @description: 研招新闻
 * @author: cycloneboy
 * 9 17
 * @create:2018-04-04 09:42
 **/
@Component
@Data
@TargetUrl("http://www.chinakaoyan.com/info/article/id/(\\d+).shtml")
@HelpUrl("http://www.chinakaoyan.com/info/list/ClassID/17/pagenum/(\\d+).shtml")
public class ResearchNewsSpider  {

    Logger logger = LoggerFactory.getLogger(ResearchNewsSpider.class);

    @Autowired
    private PubmedNewsService pubmedNewsService;


    @ExtractBy("//*[@id='navchange_con']/div/a[5]/regex('(\\d+)')")
    private String id;


    @ExtractBy("//div[@class='arcont']/h1/text()")
    private String title;


    @ExtractBy("//div[@class='arcont']/div[4]/tidyText()")
    private String content;

    @Formatter("yyyy-MM-dd")
    @ExtractBy("//div[@class='time']/regex('\\d+-\\d+-\\d+')")
    private Date date;

    private PubmedNews pubmedNews;

    public PubmedNews getPubmedNews(){
        pubmedNews.setId(this.id);
        pubmedNews.setTitle(this.title);
        pubmedNews.setContent(this.content);
        pubmedNews.setDate(this.getDate());

        return this.pubmedNews;
    }


    public void afterProcess(Page page) {
        PubmedNews pubmedNews = new PubmedNews(this.id,this.title,this.content,this.date);

//        logger.info(pubmedNews.toString());

        if(pubmedNews.getId() != null && pubmedNews.getTitle() != null){
            logger.info("存储一条数据: " + pubmedNews.getId() + " , " + pubmedNews.getTitle());

            pubmedNewsService.save(pubmedNews);
        }

    }


    public void startCrawer(String starturl){

    }

    public static void main1(String[] args) {

        String starturl = "http://www.chinakaoyan.com/info/list/ClassID/9/pagenum/1.shtml";

        OOSpider.create(Site.me()
                        .setUserAgent(ConstanCrawer.getUserAgentRandom())
                        .setDomain("chinakaoyan.com")
                        .setTimeOut(10000)
                        .setSleepTime(3)
                        .setCharset("utf-8")
                        .setCycleRetryTimes(3)
                        .setRetryTimes(3),
                new ConsolePageModelPipeline(), ResearchNewsSpider.class)
                .addUrl(starturl)
                .thread(4)
                .addPipeline(new ConsolePipeline())
                .addPipeline(new JsonFilePipeline("D:\\webmagic\\"))
//                .addPipeline(new PubmedNewsElasticsearchPipeline())
                .run();
    }

    public static void main(String[] args) {
        String starturl = "http://www.chinakaoyan.com/info/list/ClassID/9/pagenum/1.shtml";

        OOSpider.create(Site.me()
                        .setUserAgent(ConstanCrawer.getUserAgentRandom())
                        .setDomain("chinakaoyan.com")
                        .setTimeOut(60000)
                        .setSleepTime(5)
                        .setCycleRetryTimes(5)
                        .setRetryTimes(5)
                ,new PubmedNewsElasticsearchPipeline()
                , ResearchNewsSpider.class)
                .addUrl(starturl)
                .thread(10)
//                .addPipeline(new ConsolePipeline())
//                .addPipeline(new JsonFilePipeline("D:\\webmagic1\\"))
//                .addPipeline(new PubmedNewsElasticsearchPipeline())
                .run();

    }

}


