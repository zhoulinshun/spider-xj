package cn.miss.spring.spider.repository;

import cn.miss.spring.spider.bean.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/25.
 */
public interface QuestionRepository extends MongoRepository<Question,String> {

}
