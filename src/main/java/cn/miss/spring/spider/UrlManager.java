package cn.miss.spring.spider;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/15.
 */
public class UrlManager {
    public static final String host = "http://www.jyeoo.com/";
    public static final String subject = "/%s/ques/search";
//    public static final String question = "http://www.jyeoo.com/math2/ques/partialques?f=0&q=bcbe4c7f-1047-430f-b83c-666c34f5ba10~2fcf542f-d98b-4a53-bccd-e69eb946eced~&lbs=&pd=1&pi=undefined&r=0.03093753588534076";
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
    public static final String question = "http://www.jyeoo.com/%s/ques/partialques?f=0&q=%s&lbs=&pd=1&pi=%s&ct=%s&r=0.03093753588534076";

    //课程列表
    public static final String menu = "http://www.jyeoo.com/%s/ques/partialcategory?q=%s";

    /**
     * id	b1034356-154a-1595-bd5e-256d11557ddf
     * tp	1
     * r	0.7453630570452472
     */
    public static final String answerUrl = "http://www.jyeoo.com/%s/ques/quesanswer";

    public static final String searchIndex = "http://www.jyeoo.com/%s/ques/search?f=0";

}
