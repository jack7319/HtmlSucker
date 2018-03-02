package net.oschina.htmlsucker;

/**
 * @author : liulq
 * @date: 创建时间: 2018/2/27 17:05
 * @version: 1.0
 * @Description: 懒汉模式
 * https://www.cnblogs.com/cielosun/p/6582333.html
 */
public class SingletonTest {

    private static SingletonTest t;

    private SingletonTest() {

    }

    public static SingletonTest getInstance() {
        if (t == null) {
            t = new SingletonTest();
        }
        return t;
    }
}
