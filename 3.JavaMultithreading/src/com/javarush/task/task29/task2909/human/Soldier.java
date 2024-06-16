package com.javarush.task.task29.task2909.human;

public class Soldier extends Human{

    public boolean isSoldier;

    public Soldier(String name, int age) {
        super(name,age);
    }

    @Override
    public void live() {
        fight();
    }

    public void fight() {
    }

}
