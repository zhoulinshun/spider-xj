package cn.miss.spring.spider.parse;

import cn.miss.spring.spider.bean.Answer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/22.
 */

@Component
public class AnswerParser implements Parser<Answer> {
    @Override
    public List<Answer> parse(String html) {
        return null;
    }
}
