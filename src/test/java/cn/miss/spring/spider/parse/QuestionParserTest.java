package test.java.cn.miss.spring.spider.parse;

import cn.miss.spring.spider.bean.Question;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/22.
 */
public class QuestionParserTest {

    @Test
    public void optionsParse() throws IOException {

        QuestionParser parser = new QuestionParser();
        final byte[] bytes = Files.readAllBytes(new File("/Users/zhoulinshun/so/xq-spider/math_option_question.html").toPath());
        final List<Question> q = parser.parse(new String(bytes));
        System.out.println("q = " + q);
    }

    @Test
    public void patterTest(){
        final Pattern compile = Pattern.compile("(（.+）)(.+)");
        final Matcher matcher = compile.matcher("下面句子没有语病的一项是（　　）");
        if (matcher.find()) {
            matcher.group();
        }
    }
}