package net.oschina.htmlsucker;

/**
 * @author : liulq
 * @date: 创建时间: 2018/2/27 17:15
 * @version: 1.0
 * @Description: 枚举方式
 * Effective Java作者Josh Bloch 提倡的方式，在我看来简直是来自神的写法。解决了以下三个问题：
 * <p>
 * (1)自由序列化。
 * <p>
 * (2)保证只有一个实例。
 * <p>
 * (3)线程安全。
 */
public enum SingletonTest3 {

    SINGLETON;

    public void otherMethod() {
        System.out.println("..");
    }

//    public static void main(String[] args) {
//        SingletonTest3.SINGLETON.otherMethod();
//    }
}
