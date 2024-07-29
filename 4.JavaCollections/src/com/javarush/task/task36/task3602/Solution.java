package com.javarush.task.task36.task3602;

import java.lang.instrument.UnmodifiableClassException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
*/

public class Solution {


    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class<?>[] declaredClasses = Collections.class.getDeclaredClasses();

        for (Class<?> temp : declaredClasses){
            if (temp.getSimpleName().equals("EmptyList")){
                return temp;
            }
        }

        return null;
    }

}
