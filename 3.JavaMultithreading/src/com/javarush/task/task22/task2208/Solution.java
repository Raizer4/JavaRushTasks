package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* 
Формируем WHERE
*/

public class Solution {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "Ivanov");
        map.put("country", "Ukraine");
        map.put("city", "Kiev");
        map.put("age", null);
        System.out.println(getQuery(map));
    }

    public static String getQuery(Map<String, String> params) {

        StringBuilder str = new StringBuilder("WHERE ");

        for (String key : params.keySet()) {
            String value = params.get(key);

            if (value == null){
                continue;
            }
            str.append(key+ " = '" + value + "'");
            str.append(" and ");
        }

        int lastIndex = str.lastIndexOf("and");
        if (lastIndex != -1){
            str = new StringBuilder(str.substring(0,lastIndex));
        }

        return str.toString();
    }

}
