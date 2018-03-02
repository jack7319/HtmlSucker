package net.oschina.htmlsucker;

import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * @author : liulq
 * @date: 创建时间: 2018/1/29 16:04
 * @version: 1.0
 * @Description: future用来做异步处理的实现。缺点：没有通知机制，无法知道Future什么时间完成
 */
public class FutureDemo {

    private static Logger logger = Logger.getLogger("logger");

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        ExecutorService executors = Executors.newCachedThreadPool();
        Future<String> future = executors.submit(() -> { // Lambda 是一个 callable， 提交后便立即执行，这里返回的是 FutureTask 实例
            logger.info("running task");
            Thread.sleep(5000);
            return "task done";
        });

        Thread.sleep(1000);

        logger.info("do something else"); // 前面的的 Callable 在其他线程中运行着，可以做一些其他的事情

        logger.info(future.get(5, TimeUnit.SECONDS));  // 这里等待 future 的执行结果，指定等待超时时间，超时会抛出java.util.concurrent.TimeoutException
    }
}
