package cn.miss.spring.spider.bean;

import cn.miss.spring.spider.Config;
import cn.miss.spring.spider.config.Subject;
import lombok.Data;

import java.util.List;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/16.
 */
@Data
public class Question {
    private String id;
    //题目描述
    private String description;
    //来源 中考真题 自主招生
    private String source;
    //该题可能出自某套试题
    private String sourceId;

    //难度
    private Config.Difficult difficult;
    //题型 选择题、填空题
    private String type;
    //题类 常考题 题错题 好题 压轴题
    private String style;
    //年份
    private String year;

    //题目中的图片
    private List<String> img;

    //所属课程
    private Subject subject;
    //所属菜单ID
    private String menuId;

    //如果是选择 则有选项
    private List<String> options;

    private List<String> trueOptions;

    private String answer;

}
