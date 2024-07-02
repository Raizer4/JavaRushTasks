package com.javarush.task.task28.task2802;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
*/

public class Solution {

    public static void main(String[] args) {

        class EmulatorThreadFactoryTask implements Runnable {

            @Override
            public void run() {
                emulateThreadFactory();
            }

        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulatorThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulatorThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {

        AmigoThreadFactory factory = new AmigoThreadFactory();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };

        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();

    }

    public static class AmigoThreadFactory implements ThreadFactory {

        private static final AtomicInteger factoryNumber = new AtomicInteger(0);
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private ThreadGroup group;
        private final int factoryId;

        public AmigoThreadFactory(){
            this.group = Thread.currentThread().getThreadGroup();
            this.factoryId = factoryNumber.incrementAndGet();
        }

        @Override
        public Thread newThread(Runnable r) {
            String threadName = String.format("%s-pool-%d-thread-%d",
                    group.getName(), factoryId, threadNumber.getAndIncrement());
            Thread thread = new Thread(group, r, threadName);
            thread.setDaemon(false);
            thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        }

    }

}
