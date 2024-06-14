package com.javarush.task.task25.task2512;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Живем своим умом
*/

public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        t.interrupt();

        List<Throwable> list = new ArrayList<>();

        list.add(e);

        Throwable cause = e.getCause();
        while (cause != null) {
            list.add(cause);
            cause = cause.getCause();
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            Throwable temp = list.get(i);
            System.out.println(temp.getClass().getName() + ": " + temp.getMessage());
        }

    }

    public static void main (String[] args){
        new Solution().uncaughtException(new Thread(), new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
    }

}