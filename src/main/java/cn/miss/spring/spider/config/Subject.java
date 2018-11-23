package cn.miss.spring.spider.config;

import cn.miss.spring.spider.bean.ClassLevel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/15.
 */
public enum Subject {
    // 初中
    math(ClassLevel.junior, "数学"),
    english(ClassLevel.junior, "英语"),
    physics(ClassLevel.junior, "物理"),
    bio(ClassLevel.junior, "生物"),//生物
    chinese(ClassLevel.junior, "语文"),
    chemistry(ClassLevel.junior, "化学"),//化学
    politics(ClassLevel.junior, "政治"),//政治
    history(ClassLevel.junior, "历史"),//
    geography(ClassLevel.junior, "地理"),//地理


    //高中
    math2(ClassLevel.senior, "数学"),
    english2(ClassLevel.senior, "英语"),
    physics2(ClassLevel.senior, "物理"),
    bio2(ClassLevel.senior, "生物"),//生物
    chinese2(ClassLevel.senior, "语文"),
    chemistry2(ClassLevel.senior, "化学"),//化学
    politics2(ClassLevel.senior, "政治"),//政治
    history2(ClassLevel.senior, "历史"),//
    geography2(ClassLevel.senior, "地理"),//地理

    //小学
    math0(ClassLevel.small, "奥数"),//奥数
    math3(ClassLevel.small, "数学"),//数学
    chinese3(ClassLevel.small, "语文"),
    english3(ClassLevel.small, "英语"),
    ;

    private final ClassLevel level;
    private final String name;

    Subject(ClassLevel level, String name) {
        this.level = level;
        this.name = name;
    }

    public ClassLevel getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public static List<Subject> getOfClassLevel(ClassLevel classLevel) {
        return Stream.of(Subject.values()).filter(s -> s.getLevel() == classLevel).collect(Collectors.toList());
    }

    public static List<String> getNameOfClassLevel(ClassLevel classLevel) {
        return Stream.of(Subject.values()).filter(s -> s.getLevel() == classLevel).map(Enum::name).collect(Collectors.toList());
    }
}
