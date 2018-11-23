package cn.miss.spring.spider.adapter;

import cn.miss.spring.spider.bean.DownType;
import cn.miss.spring.spider.parse.Parser;

import java.util.List;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/18.
 */
public interface Adapter<T, R> {

    DownType getDownType();

    Parser<T> getParser();

    String getPrefix();

    String getUrl(String... id);

    List<T> adapter(R r);

}
