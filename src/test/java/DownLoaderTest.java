package test.java;

import cn.miss.spring.spider.adapter.BookAdapter;
import cn.miss.spring.spider.bean.Book;
import cn.miss.spring.spider.config.Subject;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/19.
 */
public class DownLoaderTest {

    @Test
    public void test() {


        final Stream<String> stream = Stream.of("abc", "efg", "hij").map(s -> s.split("")).flatMap(Stream::of);
        final Observable<String[]> map = Observable.fromArray("abc", "efg", "hij").map(s -> s.split(""));
        map.flatMap(Observable::fromArray).subscribe(System.out::println).dispose();
        map.subscribe(System.out::println);


//        final Observable<char[]> observable = Observable.fromArray("abc", "efg", "hij").map(String::toCharArray).flatMap(Observable::fromArray);
    }

    @Test
    public void test2() throws InterruptedException {
        final BookAdapter bookAdapter = new BookAdapter();
        Observable.fromArray(Subject.values()).
                subscribeOn(Schedulers.newThread()).
                flatMapIterable(bookAdapter::adapter).
                observeOn(Schedulers.single() ).
                subscribe(System.out::println,Throwable::printStackTrace).dispose();
        new CountDownLatch(1).await();
//                blockingSubscribe(System.out::println);
    }

    @Test
    public void test4(){
        Observable.create(e -> {
            for (int i = 0; i < 10; i++) {
                e.onNext(i);
            }
            e.onComplete();
        }).subscribeOn(Schedulers.single()).subscribe(System.out::println).dispose();
        Observable.fromArray("abc","bcd").
                subscribeOn(Schedulers.single()).
                subscribe(System.out::println,Throwable::printStackTrace);
    }

    @Test
    public void test5() throws IOException {
        Observable.fromArray("Abc","bcd","efg","nfc").observeOn(Schedulers.newThread()).flatMapIterable(s-> Arrays.asList(s.split(""))).
                observeOn(Schedulers.newThread()).subscribeOn(Schedulers.newThread()).
                subscribe(System.out::println);
        System.in.read();
    }

    @Test
    public void test3() {
        Observable.create((ObservableOnSubscribe<Integer>) observableEmitter -> {
            System.out.println("ObservableOnSubscribe.subscribe thread : " + Thread.currentThread().getName());
            observableEmitter.onNext(1);
            observableEmitter.onComplete();
        }).subscribeOn(Schedulers.io())
                .map(integer -> {
                    System.out.println("map  thread : " + Thread.currentThread().getName());
                    return String.valueOf(integer);
                })
                .subscribeOn(Schedulers.newThread())
                .filter(s -> {
                    System.out.println("filter  thread : " + Thread.currentThread().getName());
                    return Integer.parseInt(s) > 0;
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        System.out.println("filter  thread : " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext  thread : " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("onError  thread : " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete  thread : " + Thread.currentThread().getName());
                    }
                });
    }
}
