package com.javarush.task.task26.task2601;

import java.util.*;

/* 
Почитать в инете про медиану выборки
*/

public class Solution {

    public static void main(String[] args) {
        Integer[] sort = sort(new Integer[]{13, 8, 15, 5, 17});

        for (Integer temp : sort){
            System.out.println(temp);
        }

    }

    public static Integer[] sort(Integer[] array) {

        List<Integer> list = new ArrayList<>();

        Integer[] result = new Integer[array.length];

        int medians = getMedians(array);

        for (int i = 0; i < array.length; i++){
            int temp = Math.abs(medians - array[i]);
            list.add(temp);
        }


        for (int j = 0; j < list.size(); j++){
            int minIndex = list.indexOf(Collections.min(list));
            result[j] = array[minIndex];
            list.set(minIndex, Integer.MAX_VALUE);
        }


        return result;
    }

    public static int getMedians(Integer[] array){

        List<Integer> list = new ArrayList<>();

        if (array.length % 2 == 0){

            int result = 0;

            for (int i = 0; i < array.length;i++){
                list.add(array[i]);
            }


            while (list.size() != 2){
                Integer min = Collections.min(list);
                list.remove(min);
                Integer max = Collections.max(list);
                list.remove(max);
            }

            for (Integer temp : list){
                result += temp;
            }

            return result / 2;


        }else {
            int result = 0;

            for (int i = 0; i < array.length;i++){
                list.add(array[i]);
            }


            while (list.size() != 1){
                Integer min = Collections.min(list);
                list.remove(min);
                Integer max = Collections.max(list);
                list.remove(max);
            }

            for (Integer temp : list){
                result += temp;
            }

            return result;
        }
    }

}
