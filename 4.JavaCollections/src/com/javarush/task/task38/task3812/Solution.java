package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Solution {

    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        if (c.isAnnotationPresent(PrepareMyTest.class)){
            PrepareMyTest annotation = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            for (String temp : annotation.fullyQualifiedNames()){
                System.out.println(temp);
            }
            return true;
        }
        return false;
    }

    public static boolean printValues(Class c) {

        if (c.isAnnotationPresent(PrepareMyTest.class)) {
            PrepareMyTest annotation = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            Class<?>[] value = annotation.value();
            for (Class temp : value){
                System.out.println(temp.getSimpleName());
            }
            return true;
        }

        return false;
    }

}
