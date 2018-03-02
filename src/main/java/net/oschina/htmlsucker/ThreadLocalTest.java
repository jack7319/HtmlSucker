package net.oschina.htmlsucker;

import java.util.concurrent.TimeUnit;

/**
 * @author : liulq
 * @date: 创建时间: 2018/2/27 16:15
 * @version: 1.0
 * @Description:
 */
public class ThreadLocalTest {

    static ThreadLocal<String> local = new ThreadLocal<String>();

    void set(String s) {
        local.set(s);
    }

    String get() {
        return local.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalTest t = new ThreadLocalTest();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.set("t1");
                try {
                    TimeUnit.MILLISECONDS.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 = " + t.get());
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.set("t2");
                System.out.println("t2 = " + t.get());
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t2.start();
        TimeUnit.SECONDS.sleep(1);
    }

}
