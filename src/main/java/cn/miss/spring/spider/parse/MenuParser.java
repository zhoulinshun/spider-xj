package cn.miss.spring.spider.parse;

import cn.miss.spring.spider.bean.Menu;
import cn.miss.spring.spider.config.Subject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: zhoulinshun
 * @Description: 课程目录解析器
 * @Date: Created in 2018/10/16.
 */
@Component
public class MenuParser implements Parser<Menu> {

    private final Subject subject;


    @Autowired
    public MenuParser(){
        this(null);
    }
    public MenuParser(Subject subject) {
        this.subject = subject;
    }

    @Override
    public List<Menu> parse(String html) {
        final Document root = Jsoup.parse(html);
        final Element body = root.body();
        final List<Menu> menus = new ArrayList<>();
        deep(body, menus);
        return menus;
    }

    private void deep(Element element, List<Menu> menus) {
        List<Menu> temp = menus;
        final String tagName = element.tagName();
        if (Objects.equals(tagName, "li")) {
            final Menu menu = new Menu();
            List<Menu> children = new ArrayList<>();
            menu.setChapter(element.attr("nm"));
            menu.setId(element.attr("pk"));
            menu.setChildren(children);
            menu.setSubject(subject);
            temp.add(menu);
            temp = children;
        }

        for (Element child : element.children()) {
            deep(child, temp);
        }

    }
}
