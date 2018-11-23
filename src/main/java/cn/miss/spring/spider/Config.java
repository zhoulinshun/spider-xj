package cn.miss.spring.spider;

import cn.miss.spring.spider.config.Subject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/12.
 */
public class Config {

    static Map<Subject, List<Exercises>> map = new HashMap<>();

    {
        map.put(Subject.math, Arrays.asList(Config.Exercises.options, Config.Exercises.exercise, Config.Exercises.aquestions));
        map.put(Subject.math2, Arrays.asList(Config.Exercises.options, Config.Exercises.exercise, Config.Exercises.aquestions));
        map.put(Subject.math0, Arrays.asList(Config.Exercises.options, Config.Exercises.exercise, Config.Exercises.aquestions));
    }

    public static List<Exercises> getAllByExercises(Subject subject) {
        return map.get(subject);
    }
    /**
     * 题型
     */
    public static enum Exercises {

        all("0", "全部"),
        options("1", "选择题"),
        exercise("2", "填空题"),
        aquestions("9", "解答题"),
        ;

        static String paramName = "ct";

        private String value;
        private String des;

        Exercises(String value, String des) {
            this.value = value;
            this.des = des;
        }

        public String getValue() {
            return value;
        }



    }

    /**
     * 难度
     */
    public static enum Difficult {
        all("0", "全部"),
        easy("11", "易"),
        easyUp("12", "较易"),
        mid("13", "中档"),
        hardLow("14", "较难"),
        hard("15", "难"),
        ;
        static String paramName = "dg";
        private final String value;
        private final String des;

        Difficult(String value, String des) {
            this.value = value;
            this.des = des;
        }
    }

    /**
     * 题类 压轴题等等
     */
    public static enum Type {
        all("0", "全部"),
        ;
        static String paramName = "fg";
        private final String value;
        private final String des;

        Type(String value, String des) {
            this.value = value;
            this.des = des;
        }
    }

    /**
     * 来源 中考真题等等
     */
    public static enum Source {

        ;
        static String paramName = "so";
        private final String value;
        private final String des;

        Source(String value, String des) {
            this.value = value;
            this.des = des;
        }
    }

    /**
     * 二级来源
     */
    public static enum Source2 {
        ;
        static String paramName = "so2";
        private final String value;
        private final String des;

        Source2(String value, String des) {
            this.value = value;
            this.des = des;
        }
    }

    /**
     * 排序规则
     */
    public static enum OrderRule {
        ;
        static String paramName = "po";
        private final String value;
        private final String des;

        OrderRule(String value, String des) {
            this.value = value;
            this.des = des;
        }
    }

    /**
     * 升序或降序
     */
    public static enum Order {
        ;
        static String paramName = "pd";
        private final String value;
        private final String des;

        Order(String value, String des) {
            this.value = value;
            this.des = des;
        }
    }

    /**
     * 年份
     */
    public static enum DTime {
        ;
        static String paramName = "yr";
        private final String value;
        private final String des;

        DTime(String value, String des) {
            this.value = value;
            this.des = des;
        }
    }

}
