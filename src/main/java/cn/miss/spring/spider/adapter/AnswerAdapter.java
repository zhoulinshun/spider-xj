package cn.miss.spring.spider.adapter;

import cn.miss.spring.spider.UrlManager;
import cn.miss.spring.spider.bean.Answer;
import cn.miss.spring.spider.bean.DownType;
import cn.miss.spring.spider.bean.Question;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/18.
 */
@Component
public class AnswerAdapter extends AbstractAdapter<Answer, Question> {

    @Override
    public DownType getDownType() {
        return DownType.answer;
    }

    @Override
    public String getPrefix() {
        return UrlManager.answerUrl;
    }

    @Override
    public List<Answer> adapter(Question question) {
        final String url = getUrl(question.getSubject().name());
        Map<String, Object> params = new HashMap<>();
        params.put("id", question.getId());
        params.put("tp", 1);
        params.put("r", new Random().nextDouble());
        final String html = getDownLoader().postWithLogin(url, params);
        return getParser().parse(html);
    }
}
