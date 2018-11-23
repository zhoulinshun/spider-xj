package cn.miss.spring.spider.down;

import java.util.Map;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/18.
 */
public interface DownLoader {
    String download(String url);

    String get(String url);

    String get(String url, Map<String, Object> urlParams);

    String getWithLogin(String url, Map<String, Object> urlParams);

    String post(String url);

    String post(String url, Map<String, Object> params);

    String postWithLogin(String url, Map<String, Object> params);

}
