package cn.miss.spring.spider.bean;

import cn.miss.spring.spider.config.Subject;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/18.
 */
@Data
@Document(collection = "xj_book")
public class Book {

    @Id
    @Indexed
    private String id;
    /**
     * 人教版
     * 苏教版等等
     */
    private String type;

    private Subject subject;

//    private String
    /**
     * 必修一 必修二
     * 高一上 高一下
     * 等等
     */
    private String className;

    private List<Menu> menus;
}
