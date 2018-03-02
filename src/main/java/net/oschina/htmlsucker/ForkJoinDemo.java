package net.oschina.htmlsucker;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author : liulq
 * @date: 创建时间: 2018/1/29 14:42
 * @version: 1.0
 * @Description: forkjoin框架，一个计算由a到b的各的Demo
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    private long a;
    private long b;
    private long threshold; // 阈值，一个任务最多计算多少个数字

    public ForkJoinDemo(long a, long b, long threshold) {
        this.a = a;
        this.b = b;
        this.threshold = threshold;
    }

    @Override
    public Long compute() {
        if (b - a < threshold) {
            long sum = 0;
            for (long i = a; i <= b; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (a + b) / 2;
            ForkJoinDemo left = new ForkJoinDemo(a, middle, threshold);
            ForkJoinDemo right = new ForkJoinDemo(middle + 1, b, threshold);
            left.fork();
            right.fork();
            return left.join() + right.join();
        }
    }

    public long getA() {
        return a;
    }

    public ForkJoinDemo setA(long a) {
        this.a = a;
        return this;
    }

    public long getB() {
        return b;
    }

    public ForkJoinDemo setB(long b) {
        this.b = b;
        return this;
    }

    public long getThreshold() {
        return threshold;
    }

    public ForkJoinDemo setThreshold(long threshold) {
        this.threshold = threshold;
        return this;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // forkjoin方式
        long time1 = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> forkJoinDemo = new ForkJoinDemo(0, 10000000000L, 1000000);
        Long submit = pool.invoke(forkJoinDemo);
        long time2 = System.currentTimeMillis();
        System.out.println("result = " + submit + "  time = " + (time2 - time1));
        pool.shutdown();
        // result = -5340232216128654848  time = 1759
        // 时间在17-21之间浮动

        // jdk8并行流方式
        long time3 = System.currentTimeMillis();
        long reduce = LongStream.rangeClosed(0, 10000000000L).parallel().reduce(0, Long::sum);
        long time4 = System.currentTimeMillis();
        System.out.println("result = " + reduce + "  time = " + (time4 - time3));
        // result = -5340232216128654848  time = 1878
        // 时间在17-25之间浮动

        // 最直接的方式
        long time5 = System.currentTimeMillis();
        long sum = 0;
        for (long i = 0; i < 10000000000L; i++) {
            sum += i;
        }
        long time6 = System.currentTimeMillis();
        System.out.println("result = " + sum + "  time = " + (time6 - time5));
        // result = -5340232226128654848  time = 2962
    }
}
