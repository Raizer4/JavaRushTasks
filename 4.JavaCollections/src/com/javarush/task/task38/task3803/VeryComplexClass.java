package com.javarush.task.task38.task3803;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {

    public void methodThrowsClassCastException() {
        Object str = "negri";
        Integer result = (Integer) str;
    }

    public void methodThrowsNullPointerException() {
        String temp = null;
        temp.length();
    }

    public static void main(String[] args) {

    }

}
