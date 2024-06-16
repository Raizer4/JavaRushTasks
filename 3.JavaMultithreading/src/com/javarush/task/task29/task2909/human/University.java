package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class University {

    private String name;
    private int age;
    private List<Student> students = new ArrayList<>();

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:

        for (Student temp : getStudents()){
            if(temp.getAverageGrade() == averageGrade){
                return temp;
            }
        }

        return null;
    }
    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        List<Double> list = new ArrayList<>();
        for (Student temp : getStudents()) {
            list.add(temp.getAverageGrade());
        }

        return getStudentWithAverageGrade(Collections.max(list));
    }
    public Student getStudentWithMinAverageGrade(){
        List<Double> list = new ArrayList<>();
        for (Student temp : getStudents()) {
            list.add(temp.getAverageGrade());
        }

        return getStudentWithAverageGrade(Collections.min(list));
    }
    public void expel(Student student){
        students.remove(student);
    }


}