package net.oschina.htmlsucker;

import java.util.concurrent.CountDownLatch;

/**
 * @author : liulq
 * @date: 创建时间: 2018/2/6 15:34
 * @version: 1.0
 * @Description: CountDownLauth模拟多个线程同时执行。模拟高并发。
 */
public class CountDownLatchDemo {

    private int c;

    public void inc() {
        c++;
    }

    public int getC() {
        return c;
    }

    public CountDownLatchDemo setC(int c) {
        this.c = c;
        return this;
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchDemo countDownLauthDemo = new CountDownLatchDemo();
        CountDownLatch order = new CountDownLatch(1);
        CountDownLatch follower = new CountDownLatch(50);
        for (int i = 0; i < 50; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        order.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < 100000; i++) {
                        countDownLauthDemo.inc();
                    }
                    follower.countDown();
                }
            });
            thread.start();
        }
        order.countDown();
        follower.await();
        System.out.println("ok! ");
        System.out.println("i = " + countDownLauthDemo.getC());
    }
}
