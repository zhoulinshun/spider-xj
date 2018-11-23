package cn.miss.spring.spider.parse;

import cn.miss.spring.spider.bean.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/18.
 */
@Component
public class BookParser implements Parser<Book> {
    @Override
    public List<Book> parse(String html) {
        final Document parse = Jsoup.parse(html);
        final Element ul = parse.getElementById("JYE_BOOK_TREE_HOLDER");
        return parse(ul);
    }

    private List<Book> parse(Element ul) {
        final Elements children = ul.children();
        List<Book> list = new ArrayList<>(children.size());
        for (Element child : children) {
            if (child.tagName().equals("li")) {
                final String nm = child.attr("nm");
                final Elements liChildren = child.getElementsByTag("li");
                for (Element liChild : liChildren) {
                    final Book book = new Book();
                    book.setType(nm);
                    final String id = liChild.attr("bk");
                    if(StringUtils.isEmpty(id)){
                        continue;
                    }
                    final String className = liChild.attr("nm");
                    book.setClassName(className);
                    book.setId(id);
                    list.add(book);
                }
            }
        }
        return list;
    }

}
