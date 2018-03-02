package net.oschina.htmlsucker;

/**
 * @author : liulq
 * @date: 创建时间: 2018/1/30 10:02
 * @version: 1.0
 * @Description:
 */
@FunctionalInterface
public interface TriFunction<T, U, S, R> {

    R apply(T t, U u, S s);
}