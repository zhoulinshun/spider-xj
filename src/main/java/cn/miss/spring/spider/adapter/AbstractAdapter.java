package cn.miss.spring.spider.adapter;

import cn.miss.spring.spider.down.DownLoader;
import cn.miss.spring.spider.down.SimpleDownLoader;
import cn.miss.spring.spider.parse.Parser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/22.
 */
public abstract class AbstractAdapter<T, R> implements Adapter<T, R> {
    @Getter
    @Autowired
    private Parser<T> parser;

    @Getter
    @Setter
    @Autowired
    private DownLoader downLoader;

    public Parser<T> getParser() {
        return parser;
    }

    @Override
    public String getUrl(String... id) {
        return String.format(getPrefix(), id);
    }
}
