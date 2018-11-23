package test.java;

import cn.miss.spring.spider.config.Subject;
import cn.miss.spring.spider.adapter.BookAdapter;
import cn.miss.spring.spider.adapter.MenuAdapter;
import cn.miss.spring.spider.bean.Book;
import cn.miss.spring.spider.bean.Menu;
import cn.miss.spring.spider.parse.BookParser;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/18.
 */
public class ParserTest {

    public static void main(String[] args) throws IOException {
        BookAdapter bookAdapter = new BookAdapter();

        final List<Book> adapter = bookAdapter.adapter(Subject.chemistry2);
        System.out.println(adapter);
    }

    @Test
    public void bookTest(){
        BookAdapter bookAdapter = new BookAdapter();

        final List<Book> adapter = bookAdapter.adapter(Subject.chemistry2);
        System.out.println(adapter);
    }



    @Test
    public void menuTest(){
        final MenuAdapter menuAdapter = new MenuAdapter();

//        final List<Menu> adapter = menuAdapter.adapter("chemistry2","5e7e07d9-b4a6-4876-b6d7-5e18eb91bbb1");
//        System.out.println("adapter = " + adapter);
    }
    public static void test() throws IOException {
        final BookParser bookParser = new BookParser();
        final byte[] bytes = Files.readAllBytes(new File("/Users/zhoulinshun/so/xq-spider/index.html").toPath());
        final List<Book> parse = bookParser.parse(new String(bytes));
        System.out.println(parse);
    }
}
