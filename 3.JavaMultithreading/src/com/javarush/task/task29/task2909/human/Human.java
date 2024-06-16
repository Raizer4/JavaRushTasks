package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive {

    private static int nextId = 0;

    private int id;

    protected int age;

    protected String name;
    private BloodGroup bloodGroup;
    private List<Human> children = new ArrayList<>();
    Size size;


    public class Size {
        public int height;
        public int weight;
    }



    public Human(String name, int age) {
        this.name = name;
        this.age = age;
        this.id = nextId;
        nextId++;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public List<Human> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public String getPosition(){
        return "Человек";
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    public void setAge(int age) {
        this.age = age;
    }


    public void addChild(Human human){
        children.add(human);
    }
    public void removeChild(Human human){
        children.remove(human);
    }


    public void printSize() {
        System.out.println("Рост: " + size.height + " Вес: " + size.weight);
    }

    public void printData() {
        System.out.println(getPosition()+": " + name);
    }

    @Override
    public void live() {

    }

}