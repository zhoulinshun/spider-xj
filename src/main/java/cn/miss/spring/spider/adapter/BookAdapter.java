package cn.miss.spring.spider.adapter;

import cn.miss.spring.spider.UrlManager;
import cn.miss.spring.spider.bean.Book;
import cn.miss.spring.spider.bean.DownType;
import cn.miss.spring.spider.bean.Menu;
import cn.miss.spring.spider.config.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/18.
 */
@Component
public class BookAdapter extends AbstractAdapter<Book, Subject> {
    private static final Logger logger = LoggerFactory.getLogger(BookAdapter.class);

    @Autowired
    private MenuAdapter menuAdapter;

    @Override
    public DownType getDownType() {
        return DownType.book;
    }


    @Override
    public String getPrefix() {
        return UrlManager.searchIndex;
    }

    /**
     * @param subject subject
     * @return
     */
    @Override
    public List<Book> adapter(Subject subject) {
        logger.info("BookAdapter.adapter: subject :[{}]", subject);
        final String url = getUrl(subject.name());
        final String html = getDownLoader().download(url);
        final List<Book> parse = getParser().parse(html);
        for (Book book : parse) {
            book.setSubject(subject);
        }
        return parse;
    }
}
