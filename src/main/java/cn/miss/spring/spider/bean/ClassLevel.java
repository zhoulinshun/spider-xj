package cn.miss.spring.spider.bean;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/18.
 */
public enum ClassLevel {
    small("小学"),
    junior("初中"),
    senior("高中"),
    ;

    private final String value;

    ClassLevel(String value) {this.value = value;}

    public String getValue() {
        return value;
    }
}
