package com.cycloneboy.bookstore.web.crawer.pipline;

import com.cycloneboy.bookstore.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * @program: bookstore
 * @description: JobInfoDaoPipeline
 * @author: cycloneboy
 * @create:2018-04-03 21:52
 **/
public class JobInfoDaoPipeline implements PageModelPipeline<Article> {




    @Override
    public void process(Article article, Task task) {

    }
}
