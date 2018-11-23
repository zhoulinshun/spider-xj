package cn.miss.spring.spider.repository;

import cn.miss.spring.spider.bean.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/24.
 */
@Repository
public interface BookRepository extends MongoRepository<Book, String> {
}
