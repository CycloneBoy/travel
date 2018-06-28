package com.cycloneboy.bookstore.Scheduled;

import com.cycloneboy.bookstore.util.constant.ConstanCrawer;
import com.cycloneboy.bookstore.web.crawer.pipline.NewsPipeline;
import com.cycloneboy.bookstore.web.crawer.postgraduate.ResearchNewsSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;

/**
 * @program: bookstore
 * @description:
 * @author: cycloneboy
 * @create:2018-04-05 20:42
 **/
@Component
public class NewsScheduled {

    @Autowired
    private NewsPipeline newsPipeline;

    @Scheduled(fixedDelay=20000)
    public void newsScheduled(){
//        System.out.println("--开始执行新闻爬取模块");
//
//        String starturl = "http://www.chinakaoyan.com/info/list/ClassID/9/pagenum/1.shtml";
//
//         OOSpider.create(Site.me()
//                        .setUserAgent(ConstanCrawer.getUserAgentRandom())
//                        .setDomain("chinakaoyan.com")
//                        .setTimeOut(60000)
//                        .setSleepTime(5)
//                        .setCycleRetryTimes(5)
//                        .setRetryTimes(5)
////                ,new PubmedNewsElasticsearchPipeline()
//                ,newsPipeline
//                , ResearchNewsSpider.class)
//                .addUrl(starturl)
//                .thread(10)
////                .addPipeline(new ConsolePipeline())
////                .addPipeline(new JsonFilePipeline("D:\\webmagic1\\"))
////                .addPipeline(new PubmedNewsPipeline())
//                .setExitWhenComplete(true)
//                 .run();

//        System.out.println("--关闭执行新闻爬取模块");

    }

}
