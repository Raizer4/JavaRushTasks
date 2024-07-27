package com.javarush.task.task35.task3509;

import java.util.*;

/* 
Collections & Generics
*/

public class Solution {

    public static void main(String[] args) {
    }

    public static <T> ArrayList<T> newArrayList(T... elements) {
        ArrayList<T> result = new ArrayList<>(elements.length);

        for (int i = 0; i < elements.length; i++){
            result.add(elements[i]);
        }

        return result;
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        HashSet<T> result = new HashSet<>(elements.length);

        for (int i = 0; i < elements.length; i++){
            result.add(elements[i]);
        }

        return result;
    }

    public static <K,V> HashMap<K,V> newHashMap(List<? extends K> keys, List<? extends V> values) {

        if (keys.size() != values.size()){
            throw new IllegalArgumentException();
        }

        HashMap<K,V> hashMap = new HashMap();

        for (int i = 0, j = 0; i < keys.size(); i++,j++){
            K k = keys.get(i);
            V v = values.get(j);
            hashMap.put(k,v);
        }


        return hashMap;
    }

}
