package com.javarush.task.task38.task3810;


public @interface Changelog {
    Revision[] value() default {};
}
