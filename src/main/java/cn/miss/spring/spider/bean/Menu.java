package cn.miss.spring.spider.bean;

import cn.miss.spring.spider.config.Subject;
import lombok.Data;

import java.util.List;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/16.
 */
@Data
public class Menu {
    private String id;
    private String parentId;
    private String chapter;
    private Subject subject;

    private List<Menu> children;

}
