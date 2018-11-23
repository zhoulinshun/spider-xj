package cn.miss.spring.spider.bean;

import lombok.Data;

import java.util.List;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/16.
 */
@Data
public class Answer {
    //
    private String questionId;
    private String des;
    private List<String> option;
    //解答
    private String answerStr;

    //分析
    private String analysis;
    //点评
    private String comment;

}
