package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {

    private List<Horse> horses = new ArrayList<>();

    public static Hippodrome game;

    public static void main(String[] args) throws InterruptedException {
        game = new Hippodrome(new ArrayList<>());
        game.horses.add(new Horse("Secretariat",3,0));
        game.horses.add(new Horse("Nyashka",3,0));
        game.horses.add(new Horse("Bojeck",3,0));
        game.run();
        game.printWinner();
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public void run() throws InterruptedException {
        for (int i = 1; i <= 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }
    public void move(){
        for (Horse temp : horses){
            temp.move();
        }
    }
    public void print(){
        for (Horse temp : horses){
            temp.print();
        }
        for (int i = 0; i < 10; i++){
            System.out.println();
        }
    }

    public Horse getWinner(){
        double maxDistance = 0;
        for (Horse temp : horses){
            if (maxDistance < temp.getDistance()){
                maxDistance = temp.getDistance();
            }
        }
        for (Horse temp : horses){
            if (maxDistance == temp.getDistance()){
                return temp;
            }
        }
        return null;
    }

    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }

}
