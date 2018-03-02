package net.oschina.htmlsucker;

/**
 * @author : liulq
 * @date: 创建时间: 2018/1/26 16:40
 * @version: 1.0
 * @Description:
 */
public interface InterfaceA {

    public static final int a = 10;

    public abstract String get();

    // static方法必须通过接口类调用
    public static void print(String msg){
        System.out.println(msg);
    }

    // default方法必须通过实现类的对象调用
    public default void print2(){
        System.out.println("default");
    }
}