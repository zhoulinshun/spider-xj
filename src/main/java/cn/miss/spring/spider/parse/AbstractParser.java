package cn.miss.spring.spider.parse;

import cn.miss.spring.spider.bean.Question;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/22.
 */
public abstract class AbstractParser implements Parser<Question> {

    @Override
    public List<Question> parse(String html) {
        final Document document = Jsoup.parse(html);
        final Elements fieldset = document.getElementsByTag("fieldset");
        return parse(fieldset);
    }

    public List<Question> parse(Elements fieldset) {
        List<Question> questions = new ArrayList<>(fieldset.size());
        for (Element element : fieldset) {
            final String subject = element.attr("s");
            final String attr = element.attr("data-cate");
            final int questionType = Integer.parseInt(attr);
            switch (questionType) {
                case 1:
                    questions.add(optionsParse(element));
                    break;
                case 2:
                    questions.add(spacesParse(element));
                    break;

                default:
                    break;
            }
        }
        return questions;
    }

    public abstract Question optionsParse(Element element);

    public abstract Question spacesParse(Element element);
}
