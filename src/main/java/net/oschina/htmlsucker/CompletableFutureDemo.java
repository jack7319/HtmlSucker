package net.oschina.htmlsucker;

import org.junit.Test;

import java.awt.image.VolatileImage;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

/**
 * @author : liulq
 * @date: 创建时间: 2018/1/29 16:19
 * @version: 1.0
 * @Description:
 */
public class CompletableFutureDemo {

    @Test
    public void runAsyncTest() throws ExecutionException, InterruptedException {
        // runAsync没有返回值
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Hello");
        });
        System.out.println(future.get()); // 这里为Null
        System.out.println("CompletableFuture");
    }

    @Test
    public void supplyAsyncTest() throws ExecutionException, InterruptedException {
        // supplyAsync有返回值
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "supply");
        System.out.println(future.get());
        System.out.println("CompletableFuture");
    }

    @Test
    public void completeTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");

        future.complete("World"); // future调用complete会立即执行。并且complete函数只能执行一次。这里打印World

        System.out.println(future.get());
    }

    @Test
    public void thenApplyTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");

//        future.thenApply((s) -> s + "World").thenApply(s -> s.toUpperCase());

        future = future.thenApply(new Function<String, String>() {

            @Override
            public String apply(String s) {

                return s + " World";
            }
        }).thenApply(new Function<String, String>() {
            @Override
            public String apply(String s) {

                return s.toUpperCase();
            }
        });

        System.out.println(future.get());

    }

    // 上个式子的简化写法
    // thenApply的功能相当于将CompletableFuture<T>转换成CompletableFuture<U>。
    @Test
    public void thenApplyTest1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");

        future = future.thenApply((s) -> s + " World").thenApply(s -> s.toUpperCase());

        System.out.println(future.get());

    }

    // thenApply从字符串转int转double
    @Test
    public void thenApplyTest2() throws ExecutionException, InterruptedException {
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> "10").thenApply(Integer::parseInt).thenApply(i -> i * 1.0);

        System.out.println(future.get());

    }

    // thenCompose将前一个的结果作为下一个的参数。结果100100.0
    // thenApply也会将上一个的结果作为下一个的参数啊。。。？
    @Test
    public void thenComposeTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> "100")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "100"))
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> Double.parseDouble(s)));
        // 这里thenCompose是串行执行的
        System.out.println(future.get());

    }

    // thenCombine组合。结果100100.0
    // thenCompose就是将CompletableFuture<T>和CompletableFuture<U>变为CompletableFuture<V>
    @Test
    public void thenCombineTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "100");
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 100);

        CompletableFuture<Double> future = future1.thenCombine(future2, (s, i) -> Double.parseDouble(s + i));
        // 这里future1、future2是并行执行的
        System.out.println(future.get());

    }

    // 结果100100.0
    // thenAcceptBoth跟thenCombine类似，但是返回CompletableFuture<Void>类型。
    @Test
    public void thenAcceptBothTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "100");
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 100);

        CompletableFuture<Void> future = future1.thenAcceptBoth(future2, (s, i) -> System.out.println(Double.parseDouble(s + i)));

        // 这里future1、future2是并行执行的
        System.out.println(future.get());

    }

    // 结果100100.0
    // whenComplete计算完成后执行特定的action
    // 当发生异常时触发
    @Test
    public void whenCompleteTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> "100")
                .thenApply(s -> s + "100")
                .handle((s, t) -> s != null ? Double.parseDouble(s) : 0);

        System.out.println(future.get());

        CompletableFuture.supplyAsync(() -> "hello world")
                .thenApply(s -> {
                    s = null;
                    int length = s.length();
                    return length;
                }).thenAccept(i -> System.out.println(i))
                .whenComplete((result, throwable) -> {

                    if (throwable != null) {
                        System.out.println("Unexpected error1:" + throwable);
                    } else {
                        System.out.println(result);
                    }

                }).exceptionally(t -> {
            System.out.println("Unexpected error:" + t);
            return null;
        });
        ;

    }

    // 结果100100.0
    // whenComplete计算完成后执行特定的action。纯消费
    @Test
    public void whenCompleteTest1() throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World")
                .thenApply(s -> s + "\nThis is CompletableFuture demo")
                .thenApply(String::toLowerCase)
                .whenComplete((result, throwable) -> System.out.println(result));

    }

    // 结果100100.0
    // accetpEither两个future任意一个执行完成就会执行
    @Test
    public void accetpEitherTest() throws ExecutionException, InterruptedException {
        Random random = new Random();

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "from future1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "from future2";
        });

        CompletableFuture<Void> future = future1.acceptEither(future2, str -> System.out.println("The future is " + str));

        future.get();

    }

    public static void main(String[] args) {

    }
}
