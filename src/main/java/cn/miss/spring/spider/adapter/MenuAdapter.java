package cn.miss.spring.spider.adapter;

import cn.miss.spring.spider.UrlManager;
import cn.miss.spring.spider.bean.Book;
import cn.miss.spring.spider.bean.DownType;
import cn.miss.spring.spider.bean.Menu;
import cn.miss.spring.spider.down.DownLoader;
import cn.miss.spring.spider.down.SimpleDownLoader;
import cn.miss.spring.spider.parse.MenuParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/18.
 */
@Component
public class MenuAdapter extends AbstractAdapter<Menu, Book> {

    @Override
    public DownType getDownType() {
        return DownType.menu;
    }

    @Override
    public String getPrefix() {
        return UrlManager.menu;
    }


    /**
     * @param book
     * @return
     */
    @Override
    public List<Menu> adapter(Book book) {
        final String url = getUrl(book.getSubject().name(), book.getId());
        final String html = getDownLoader().post(url, Collections.singletonMap("a", book.getId()));
        final List<Menu> parse = new MenuParser(book.getSubject()).parse(html);
        book.setMenus(parse);
        return parse;
    }

}
