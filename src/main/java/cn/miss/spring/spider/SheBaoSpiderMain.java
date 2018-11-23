package cn.miss.spring.spider;

import cn.miss.spring.spider.down.DownLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/11/3.
 */
@Component
public class SheBaoSpiderMain {

    private String sbIndex = "http://www.bjrbj.gov.cn/LDJAPP/search/ddyy/ddyy_02_outline_new.jsp?sno=%s&spage=0&epage=10&leibie=&suoshu=&sword=";

    @Autowired
    private DownLoader downLoader;

    public void start(String start) {
        for (int i = 0; i < 24; i++) {
            final String s = downLoader.get(String.format(sbIndex, i * 20));
        }
    }
}
