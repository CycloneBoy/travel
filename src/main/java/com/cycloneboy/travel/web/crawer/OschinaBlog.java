package com.cycloneboy.bookstore.web.crawer;

import com.baomidou.mybatisplus.activerecord.Model;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.io.Serializable;
import java.util.List;

/**
 * @program: bookstore
 * @description: OschinaBlog
 * @author: cycloneboy
 * @create:2018-04-03 21:46
 **/
//TargetUrl的意思是只有以下格式的URL才会被抽取出生成model对象
//这里对正则做了一点改动，'.'默认是不需要转义的，而'*'则会自动被替换成'.*'，因为这样描述URL看着舒服一点...
//继承jfinal中的Model
//实现AfterExtractor接口可以在填充属性后进行其他操作
@TargetUrl("http://my.oschina.net/flashsword/blog/*")
public class OschinaBlog extends Model<OschinaBlog> implements AfterExtractor {


    /**
     * 主键值
     */
    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public void afterProcess(Page page) {

    }
}
