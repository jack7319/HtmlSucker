package net.oschina.htmlsucker;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : liulq
 * @date: 创建时间: 2018/1/30 15:53
 * @version: 1.0
 * @Description:
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (null == is) {
                        return super.loadClass(name, resolve);
                    }

                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException(name);
                }

            }
        };

        Object obj = classLoader.loadClass("net.oschina.htmlsucker.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof ClassLoaderTest);
        System.out.println(new ClassLoaderTest() instanceof ClassLoaderTest);
    }
}
