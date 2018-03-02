package net.oschina.htmlsucker;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author : liulq
 * @date: 创建时间: 2018/1/31 14:31
 * @version: 1.0
 * @Description:
 */
public class TestMap {
    // we assume NB_ENTITIES is divisible by NB_TASKS
    static final int NB_ENTITIES = 20_000_000, NB_TASKS = 2;
    static Map<String, String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try {
            System.out.printf("running with nb entities = %,d, nb tasks = %,d, VM args = %s%n", NB_ENTITIES, NB_TASKS, ManagementFactory.getRuntimeMXBean().getInputArguments());
            ExecutorService executor = Executors.newFixedThreadPool(NB_TASKS);
            int entitiesPerTask = NB_ENTITIES / NB_TASKS;
            List<Future<?>> futures = new ArrayList<>(NB_TASKS);
            long startTime = System.nanoTime();
            for (int i = 0; i < NB_TASKS; i++) {
                MyTask task = new MyTask(i * entitiesPerTask, (i + 1) * entitiesPerTask - 1);
                futures.add(executor.submit(task));
            }
            for (Future<?> f : futures) {
                f.get();
            }
            long elapsed = System.nanoTime() - startTime;
            executor.shutdownNow();
            System.gc();
            Runtime rt = Runtime.getRuntime();
            long usedMemory = rt.maxMemory() - rt.freeMemory();
            System.out.printf("processing completed in %,d ms, usedMemory after GC = %,d bytes%n", elapsed / 1_000_000L, usedMemory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class MyTask implements Runnable {
        private final int startIdx, endIdx;

        public MyTask(final int startIdx, final int endIdx) {
            this.startIdx = startIdx;
            this.endIdx = endIdx;
        }

        @Override
        public void run() {
            long startTime = System.nanoTime();
            for (int i = startIdx; i <= endIdx; i++) {
                map.put("sambit:rout:" + i, "C:\\Images\\Provision_Images");
            }
            long elapsed = System.nanoTime() - startTime;
            System.out.printf("task[%,d - %,d], completed in %,d ms%n", startIdx, endIdx, elapsed / 1_000_000L);
        }
    }
}
