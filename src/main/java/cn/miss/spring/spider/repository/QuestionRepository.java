package cn.miss.spring.spider.repository;

import cn.miss.spring.spider.bean.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/25.
 */
@Repository
public interface QuestionRepository extends MongoRepository<Question,String> {

}
