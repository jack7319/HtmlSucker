package net.oschina.htmlsucker;

/**
 * @author : liulq
 * @date: 创建时间: 2018/2/26 13:50
 * @version: 1.0
 * @Description:
 */
public class VolatileTest {
    public static volatile int c = 0;        //3

    public static void increase() {
        c++;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        increase();
                    }
                }
            }
            ).start();
        }
        Thread.sleep(5000);
        System.out.println(c);
    }
}
