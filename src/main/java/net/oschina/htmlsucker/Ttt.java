package net.oschina.htmlsucker;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : liulq
 * @date: 创建时间: 2018/1/30 14:01
 * @version: 1.0
 * @Description:
 */
public class Ttt {

    private long i = 0;

    public long getI() {
        return i;
    }

    public Ttt setI(long i) {
        this.i = i;
        return this;
    }

    public void inc() {
        i++;
    }

    static class Parent {
        static int a = 3;

        static {
            a = 5;
        }
    }

    static class Child extends Parent {
        static int b = a;
    }

    public static void main(String[] args) throws InterruptedException {
//        System.out.println(Child.b);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1", "1");
        hashMap.put("1", "2");
        hashMap.get("1");

        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("1", "1");
        map.get("1");

        Ttt ttt = new Ttt();
        for (int i = 0; i < 50; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        ttt.inc();
                    }
                }
            });
            thread.start();
            thread.join(); // 在这里主线程会停止。直到thread线程执行完毕。不会再new下一个线程，所以这里其实是没有多线程运行的。
        }

        System.out.println("ok! ");
        System.out.println("i = " + ttt.getI());
    }
}
