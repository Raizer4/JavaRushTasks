package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/

public class Solution {

    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> result = new HashSet<>();
        File dir = new File(pathToAnimals);

        // фильтр для выбора .class файлов
        FileFilter classFileFilter = file -> file.isFile() && file.getName().endsWith(".class");

        // получаем все файлы .class из директории
        File[] files = dir.listFiles(classFileFilter);

        if (files == null) {
            return result;
        }

        try {
            // создаем URLClassLoader для загрузки классов из указанной директории
            URL[] urls = {dir.toURI().toURL()};
            ClassLoader classLoader = new URLClassLoader(urls);

            for (File file : files) {
                String className = file.getName().replace(".class", "");

                try {
                    // загружаем класс по имени
                    Class<?> cls = classLoader.loadClass(className);

                    // проверяем, реализует ли класс интерфейс Animal
                    if (Animal.class.isAssignableFrom(cls)) {
                        // проверяем наличие публичного конструктора без параметров
                        Constructor<?> constructor = null;
                        try {
                            constructor = cls.getConstructor();
                        } catch (NoSuchMethodException ignored) {
                        }

                        if (constructor != null) {
                            // создаем экземпляр класса и добавляем его в множество
                            Animal animal = (Animal) constructor.newInstance();
                            result.add(animal);
                        }
                    }
                } catch (ClassNotFoundException | InstantiationException |
                         IllegalAccessException | InvocationTargetException e) {
                    // обрабатываем исключения, если класс не может быть загружен или экземпляр не может быть создан
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
