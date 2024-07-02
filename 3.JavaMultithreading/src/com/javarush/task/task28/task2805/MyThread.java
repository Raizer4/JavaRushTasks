package com.javarush.task.task28.task2805;

public class MyThread extends Thread{
    private static int priorityCounter = Thread.MIN_PRIORITY;

    public MyThread() {
        super();
        init();
    }

    public MyThread(Runnable target) {
        super(target);
        init();
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        init();
    }

    public MyThread(String name) {
        super(name);
        init();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        init();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        init();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        init();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        init();
    }

    private void init() {
        synchronized (MyThread.class) {

            int priority = priorityCounter;

            priorityCounter++;

            if (priorityCounter > Thread.MAX_PRIORITY) {
                priorityCounter = Thread.MIN_PRIORITY;
            }

            if (this.getThreadGroup() != null) {
                priority = Math.min(priority, this.getThreadGroup().getMaxPriority());
            }

            this.setPriority(priority);
        }
    }

}
