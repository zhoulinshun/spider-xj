package cn.miss.spring.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/16.
 */
@SpringBootApplication(scanBasePackages = "cn.miss")
public class Main implements CommandLineRunner {

    private static ConfigurableApplicationContext run;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        run = SpringApplication.run(Main.class, args);
        countDownLatch.await();
    }

    @Autowired
    SpiderMain spiderMain;

    @Override
    public void run(String... args) throws Exception {
        spiderMain.start();
    }
}
