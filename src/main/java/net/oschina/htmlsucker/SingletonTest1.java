package net.oschina.htmlsucker;

/**
 * @author : liulq
 * @date: 创建时间: 2018/2/27 17:12
 * @version: 1.0
 * @Description: 饿汉模式
 */
public class SingletonTest1 {

    private static SingletonTest1 t = new SingletonTest1();

    private SingletonTest1() {

    }

    public static SingletonTest1 getInstance() {
        return t;
    }
}
