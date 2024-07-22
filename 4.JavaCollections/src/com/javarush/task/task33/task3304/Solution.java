package com.javarush.task.task33.task3304;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.annotation.Annotation;

/* 
Конвертация из одного класса в другой используя JSON Ӏ 3304
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        Second s = (Second) convertOneToAnother(new First(), Second.class);
        First f = (First) convertOneToAnother(new Second(), First.class);
    }

    public static Object convertOneToAnother(Object one, Class resultClassObject) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(one);
        jsonString = jsonString.replaceFirst(one.getClass().getSimpleName().toLowerCase(), resultClassObject.getSimpleName().toLowerCase());
        return mapper.readValue(jsonString, resultClassObject);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "className")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Second.class, name = "second")
    })
    public static class First {
        public int i;
        public String name;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "className")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = First.class, name = "first")
    })
    public static class Second {
        public int i;
        public String name;
    }

}
