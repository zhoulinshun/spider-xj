package cn.miss.spring.spider.adapter;

import cn.miss.spring.spider.Config;
import cn.miss.spring.spider.UrlManager;
import cn.miss.spring.spider.bean.DownType;
import cn.miss.spring.spider.bean.Menu;
import cn.miss.spring.spider.bean.Question;
import cn.miss.spring.spider.down.DownLoader;
import cn.miss.spring.spider.parse.Parser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/18.
 */
@Component
public class QuestionAdapter extends AbstractAdapter<Question, Menu> {

    @Override
    public DownType getDownType() {
        return DownType.question;
    }

    @Override
    public String getPrefix() {
        return UrlManager.question;
    }
    /**
     * q 对应的id
     * pi 页码  1-100
     * f=0 固定值
     * lbs为空
     * r
     * ct 题型 0为所有 1为选择题 2为填空题
     * <p>
     * 9ff602ef-4ad0-4b35-8abf-691004f243ca~a5526436-6861-4446-81ac-99daefd9cf8c~
     */
//    public static final String question = "http://www.jyeoo.com/math2/ques/partialques?f=0&q=%s&lbs=&pd=1&pi=undefined&ct=&r=0.03093753588534076";

    /**
     * @param id id,pi,ct = 0
     * @return
     */
    @Override
    public String getUrl(String... id) {
        if (id == null || id.length == 0) {
            throw new IllegalArgumentException();
        }
        if (id.length == 1) {
            return String.format(getPrefix(), id[0], 1, 0);
        }
        if (id.length == 2) {

            return String.format(getPrefix(), id[0], id[1], 0);
        }
        return String.format(getPrefix(), id);
    }

    /**
     * @param menu id
     * @return
     */
    @Override
    public List<Question> adapter(Menu menu) {
        final DownLoader downLoader = getDownLoader();
        final Parser<Question> parser = getParser();
        return Config.getAllByExercises(menu.getSubject()).
                parallelStream().
                map(Config.Exercises::getValue).
                flatMap(value->makeUrl(value,menu)).
                map(downLoader::download).
                map(parser::parse).
                flatMap(List::stream).
                collect(Collectors.toList());
    }

    public Stream<String> makeUrl(String value, Menu menu) {
        return IntStream.range(1,100).mapToObj(page -> getUrl(menu.getSubject().name(), menu.getId(), page + "", value));
    }

}
