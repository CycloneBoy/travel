package com.cycloneboy.bookstore.web.crawer;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @program: bookstore
 * @description: OschinaBlogPageProcesser
 * @author: cycloneboy
 * @create:2018-04-03 20:46
 **/
public class OschinaBlogPageProcesser implements PageProcessor{

    private Site site = Site.me().setDomain("my.oschina.net");

    @Override
    public void process(Page page) {
        List<String> links = page.getHtml().links().regex("http://my\\.oschina\\.net/flashsword/blog/\\d+").all();
        page.addTargetRequests(links);
        page.putField("title", page.getHtml().xpath("//div[@class='BlogEntity']/div[@class='BlogTitle']/h1").toString());
        page.putField("content", page.getHtml().$("div.content").toString());
        page.putField("tags",page.getHtml().xpath("//div[@class='BlogTags']/a/text()").all());
    }

    @Override
    public Site getSite() {
        return site;

    }

    public static void main(String[] args) {
        Spider.create(new OschinaBlogPageProcesser())
                .addUrl("http://my.oschina.net/flashsword/blog")
                .addPipeline(new JsonFilePipeline("D:\\webmagic\\"))
                .addPipeline(new ConsolePipeline())
                //开启5个线程抓取
                .thread(5)
                .run();

    }
}
