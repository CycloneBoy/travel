package com.cycloneboy.bookstore.web.crawer.postgraduate;

import com.cycloneboy.bookstore.web.crawer.QQMeishi;
import lombok.Data;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.*;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

import java.util.Date;

/**
 * @program: bookstore
 * @description: 考研招生简章
 * @author: cycloneboy
 * @create:2018-04-03 22:09
 **/
@Data
@TargetUrl("http://www.chinakaoyan.com/info/article/id/(\\d+).shtml")
@HelpUrl("http://www.chinakaoyan.com/info/list/ClassID/17/pagenum/(\\d+).shtml")
public class AdmissionsGuideSpider {


    @ExtractBy("//*[@id='navchange_con']/div/a[5]/regex('(\\d+)')")
    private String id;

    @ExtractBy("//div[@class='arcont']/h1/text()")
    private String title;

    @ExtractBy("//div[@class='arcont']/div[4]/tidyText()")
    private String content;

    @Formatter("yyyy-MM-dd")
    @ExtractBy("//div[@class='time']/regex('\\d+-\\d+-\\d+')")
    private Date date;


    public static void main(String[] args) {

        String useragent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/64.0.3282.186 Safari/537.36";

        OOSpider.create(Site.me()
                        .setUserAgent(useragent)
                        .setDomain("chinakaoyan.com")
                        .setTimeOut(3000)
                        .setRetryTimes(3),
                new ConsolePageModelPipeline(), AdmissionsGuideSpider.class)
                .addUrl("http://www.chinakaoyan.com/info/list/ClassID/17/pagenum/1.shtml")
                .thread(10)
                .addPipeline(new ConsolePipeline())
                .addPipeline(new JsonFilePipeline("D:\\webmagic\\"))
                .run();
    }

}
