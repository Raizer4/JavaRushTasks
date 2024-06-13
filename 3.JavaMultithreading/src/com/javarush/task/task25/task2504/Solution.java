package com.javarush.task.task25.task2504;

/* 
Switch для нитей
*/

public class Solution {

    public static void processThreads(Thread... threads) {

        for (Thread temp : threads){

            switch (temp.getState()){
                case NEW:
                    temp.start();
                    break;
                case TIMED_WAITING:
                case WAITING:
                case BLOCKED:
                    temp.interrupt();
                    break;
                case RUNNABLE:
                    temp.isInterrupted();
                    break;
                case TERMINATED:
                    System.out.println(temp.getPriority() );
                    break;
            }

        }




    }

    public static void main(String[] args) {
    }



}
