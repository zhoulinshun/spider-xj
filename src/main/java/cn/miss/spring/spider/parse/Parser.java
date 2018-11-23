package cn.miss.spring.spider.parse;

import java.util.List;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/16.
 */
public interface Parser<PK> {
    List<PK> parse(String html);
}
