package net.oschina.htmlsucker;

/**
 * @author : liulq
 * @date: 创建时间: 2018/2/6 15:31
 * @version: 1.0
 * @Description:
 */
public class SleepDemo {

    private int c = 0;

    public void inc() {
        c++;
    }

    public int getC() {
        return c;
    }

    public SleepDemo setC(int c) {
        this.c = c;
        return this;
    }

    public static void main(String[] args) throws InterruptedException {
        SleepDemo sleepDemo = new SleepDemo();
        for (int i = 0; i < 50; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100000; i++) {
                        sleepDemo.inc();
                    }
                }
            });
            thread.start();
            thread.join(); // 在这里主线程会停止。直到thread线程执行完毕。不会再new下一个线程，所以这里其实是没有多线程运行的。
            // 模拟高并发应该使用CountDownLauth
        }
        System.out.println("ok! ");
        System.out.println("i = " + sleepDemo.getC());
    }
}
