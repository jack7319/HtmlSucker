package net.oschina.htmlsucker;

/**
 * @author : liulq
 * @date: 创建时间: 2018/2/27 17:17
 * @version: 1.0
 * @Description: 双重检测锁方式
 */
public class SingletonTest4 {

    private volatile static SingletonTest4 t;

    private SingletonTest4() {

    }

    public static SingletonTest4 getInstance() {
        if (t == null) {
            synchronized (SingletonTest4.class) {
                if (t == null) {
                    t = new SingletonTest4();
                }
            }
        }
        return t;
    }
}
