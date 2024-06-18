package com.javarush.task.task26.task2611;


import java.util.concurrent.ConcurrentHashMap;

public class Producer implements Runnable{

    private ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    public void run() {
        try{
            Thread currentThread = Thread.currentThread();
            int i = 1;
            String text = "Some text for ";
            while (!currentThread.isInterrupted()) {
                map.put(String.valueOf(i),text + i);
                i++;
                Thread.sleep(500);
            }
        }catch (Exception e){
            System.out.println(Thread.currentThread().getName() + " thread was terminated");
        }

    }

}
