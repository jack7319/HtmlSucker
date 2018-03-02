package net.oschina.htmlsucker;

/**
 * @author : liulq
 * @date: 创建时间: 2018/2/27 17:14
 * @version: 1.0
 * @Description: 静态内部类加载
 */
public class SingletonTest2 {

    private static class SingletonHolder {
        private static SingletonTest2 t = new SingletonTest2();
    }

    private SingletonTest2() {

    }

    public static SingletonTest2 getInstance() {
        return SingletonHolder.t;
    }
}
