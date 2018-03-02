package net.oschina.htmlsucker;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.LongStream;

/**
 * @author : liulq
 * @date: 创建时间: 2018/1/29 13:59
 * @version: 1.0
 * @Description: java8函数式编程
 */
public class DDD {

    public static void main(String[] args) throws InterruptedException {
//        long l = System.currentTimeMillis();
//        long reduce = LongStream.rangeClosed(0, 10000000000L).parallel().reduce(0, Long::sum);
//        long l1 = System.currentTimeMillis();
//        System.out.println("invoke = " + reduce + "  time: " + (l1 - l));
//        //invoke = -5340232216128654848  time: 15531
//        int reduce1 = IntStream.rangeClosed(0, 100).parallel().reduce(0, Integer::sum);
//        System.out.println(reduce1);
//
//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
//        Integer reduce2 = list.parallelStream().reduce(Integer::sum).get();
//        System.out.println(reduce2);

        long reduce1 = LongStream.rangeClosed(0, 5).parallel().reduce(0, Long::sum);
        System.out.println(reduce1);
        long reduce2 = LongStream.rangeClosed(0, 5).parallel().reduce(0, (a, b) -> a + b);
        System.out.println(reduce2);

        BiFunction<Integer, Integer, Integer> f = (x, y) -> (x + 2 * y) * y;
//        BiFunction<Integer, Integer, Integer> f = (x, y) -> {return (x + 2 * y) * y;};
        Integer apply = f.apply(1, 2);
        System.out.println(apply);

        DoubleFunction<String> d = x -> x * 2 + "";
        System.out.println(d.apply(2));

        Function<Integer, Integer> f1 = x -> x * x;
        System.out.println(f1.apply(3));

        IntFunction<Integer> i = x -> x * x;
        System.out.println(i.apply(3));

        TriFunction<Integer, Integer, Integer, Integer> tf = (x, y, z) -> (x + y) * z;
        System.out.println(tf.apply(1, 2, 3));

        Function<Integer, Function<Integer, Function<Integer, Integer>>> f2 = x -> y -> z -> (x + y) * z;

        IntFunction<IntFunction<IntFunction>> f3 = x -> y -> z -> (x + y) * z;

        System.out.println(f2.apply(1).apply(2).apply(3));
        System.out.println(f3.apply(1).apply(2).apply(3));

        ArrayList<String> strings = new ArrayList<>();
        for (int s = 1; s < 100; s++) {
            strings.add(String.valueOf(s));
        }
        strings.remove(1);

        Vector<String> strings2 = new Vector<>();
        strings2.add("#");

        CopyOnWriteArrayList<String> strings3 = new CopyOnWriteArrayList<>();
        strings3.add("23");

        LinkedList<String> strings1 = new LinkedList<>();
        strings1.add("2");

        HashMap<String, String> hashMap = new HashMap<>(3);
        hashMap.put("1", "1");
        for (int j = 0; j < 1000; j++) {
            hashMap.put(String.valueOf(j), String.valueOf(j));
        }

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("1", "1");

        LinkedHashMap<String, String> stringStringLinkedHashMap = new LinkedHashMap<>();
        stringStringLinkedHashMap.put("1", "1");
        stringStringLinkedHashMap.put("2", "2");
        stringStringLinkedHashMap.put("3", "3");

//        for (Map.Entry<String, String> s : stringStringLinkedHashMap.entrySet()) {
//            System.out.println(s.getKey() + " = " + s.getValue());
//        }
        stringStringLinkedHashMap.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });

        TreeMap<String, String> stringStringTreeMap = new TreeMap<>();
        stringStringTreeMap.put("!", "!");

        synchronized (new Object()) {

        }

        ReentrantLock lo = new ReentrantLock();
        lo.lock();
        lo.tryLock();
        lo.tryLock(100, TimeUnit.SECONDS);

        print("1","@");
    }

    private static void print(String... s) {
        String[] ss = s;
        System.out.println(ss.length);
    }

}
