package cn.miss.spring.spider;

import cn.miss.spring.spider.adapter.AnswerAdapter;
import cn.miss.spring.spider.adapter.BookAdapter;
import cn.miss.spring.spider.adapter.MenuAdapter;
import cn.miss.spring.spider.adapter.QuestionAdapter;
import cn.miss.spring.spider.bean.Book;
import cn.miss.spring.spider.bean.Menu;
import cn.miss.spring.spider.bean.Question;
import cn.miss.spring.spider.config.Subject;
import cn.miss.spring.spider.repository.BookRepository;
import cn.miss.spring.spider.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static cn.miss.spring.util.util.StreamUtils.flatMapIte;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/22.
 */
@Component
public class SpiderMain {
    private static final Logger logger = LoggerFactory.getLogger(SpiderMain.class);

    private static ExecutorService executorService;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private BookAdapter bookAdapter;
    @Autowired
    private MenuAdapter menuAdapter;
    @Autowired
    private AnswerAdapter answerAdapter;
    @Autowired
    private QuestionAdapter questionAdapter;


    @Autowired
    private void init() {
        executorService = Executors.newFixedThreadPool(20);
    }

    public void start() {
        executorService.execute(() -> {
            List<Book> all = bookRepository.findAll();
            if (CollectionUtils.isEmpty(all)) {
                logger.info("start spider");
                all = Stream.of(Subject.values()).parallel().map(bookAdapter::adapter).flatMap(List::stream).collect(Collectors.toList());
                final List<Book> temp = all;
                executorService.submit(() -> bookRepository.saveAll(temp));
            }
            all.parallelStream().flatMap(flatMapIte(Book::getMenus)).map(questionAdapter::adapter).filter(qs -> {
                questionRepository.saveAll(qs);
                return true;
            }).flatMap(List::stream).flatMap(flatMapIte(answerAdapter::adapter)).forEach((s) -> {});


            final Stream<Question> questionStream = all.parallelStream().
                    flatMap(flatMapIte(Book::getMenus)).
                    flatMap(flatMapIte(SpiderMain::reduce)).
                    flatMap(flatMapIte(questionAdapter::adapter));
        });

        logger.info("spider end");
//        bookObservable.
//                flatMapIterable(Book::getMenus).
//                flatMapIterable(SpiderMain::reduce).
//                flatMapIterable(questionAdapter::adapter).
//                flatMapIterable(answerAdapter::adapter).subscribe();

    }


    public static List<Menu> reduce(Menu menu) {
        final List<Menu> children = menu.getChildren();
        if (children == null) {
            return Collections.singletonList(menu);
        } else {
            return children.parallelStream().map(SpiderMain::reduce).flatMap(List::stream).collect(Collectors.toList());
        }
    }
}
