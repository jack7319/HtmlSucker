package net.oschina.htmlsucker;

/**
 * @author : liulq
 * @date: 创建时间: 2018/2/27 9:20
 * @version: 1.0
 * @Description:
 */
public class OutClass {

    private String a = "a";
    private static String b = "b";

    private void f() {

        class s{ // 局部类，只能在定义的方法中使用

        }
    }

    private static void g() {

    }

    // 非静态内部类
    class NestedInnerClass {

        private String a = "aa";

        public void print() {
            System.out.println(a); // 相同变量名，发生隐匿现象，访问的是内部类的变量
            System.out.println(OutClass.this.a); // 访问的是外部类变量
            System.out.println(b);
            f();
            g();
        }
    }

    // 静态内部类
    static class StaticInnerClass {

        public void print() {
//            System.out.println(a); // 编译报错
            System.out.println(b);
//            f(); // 编译报错
            g();
        }
    }

    public static void main(String[] args) {
        // 静态内部类，直接new
        StaticInnerClass staticInnerClass = new StaticInnerClass();
        // 非静态内部类，用外部类对象来new
        NestedInnerClass nestedInnerClass = new OutClass().new NestedInnerClass();
        // 匿名内部类
        Father father = new Father() {

            private static final int aa = 0;
//            private static int a; // 编译报错
//
//            public static void c(){  // 编译报错
//
//            }

            @Override
            public void print() {
                System.out.println(b); // 可以访问外部类的静态变量或方法
                System.out.println("father..");
            }
        };
        nestedInnerClass.print();
        staticInnerClass.print();
        father.print();
    }
}

abstract class Father {

    public abstract void print();
}

// 成员内部类
class MemberClass {

}

/**
 * 非静态内部类特点
 * 1.不能有静态成员变量或方法
 * 2.实例化非静态内部类，需要依赖外部类对象
 * 如：InnerClass inner = new OutClass().new InnerClass();
 * 3.可以访问外部类的静态/非静态成员变量或方法
 * <p>
 * <p>
 * 静态内部类特点
 * 1.可以有静态/非静态成员变量或方法
 * 2.实例化静态内部类，不需要依赖外部类对象
 * 如：InnerClass inner = new InnerClass();
 * 3.只能访问外部类的静态成员变量或方法
 * <p>
 * <p>
 * 匿名内部类特点
 * 1.没有类名，也就没有构造方法
 * 2.在创建匿名内部类的时候，就会立即创建匿名内部类的对象，所以不能是抽象类，必须实现接口或抽象父类的所有抽象方法
 * 3.不能有static修饰的变量和方法，但是可以有static final
 * 4.匿名内部类只能访问外部的static修饰的变量和方法
 */
