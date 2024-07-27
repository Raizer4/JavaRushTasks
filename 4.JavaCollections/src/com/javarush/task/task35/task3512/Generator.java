package com.javarush.task.task35.task3512;

public class Generator<T> {

    private Class<T> eventClass;

    public Generator(Class<T> eventClass) {
        this.eventClass = eventClass;
    }

    T newInstance() throws InstantiationException, IllegalAccessException {
        return eventClass.newInstance();
    }

}
