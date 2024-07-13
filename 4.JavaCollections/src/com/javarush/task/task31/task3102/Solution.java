package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/* 
Находим все файлы
*/

public class Solution {

    public static List<String> getFileTree(String root) throws IOException {
        List<String> result = new ArrayList<>();
        Queue<File> queue = new LinkedList<>();
        File rootDir = new File(root);

        if (rootDir.exists() && rootDir.isDirectory()) {
            queue.add(rootDir);

            while (!queue.isEmpty()) {
                File currentDir = queue.poll();
                File[] files = currentDir.listFiles();

                if (files != null) {

                    for (File file : files) {

                        if (file.isDirectory()) {
                            queue.add(file);
                        } else {
                            result.add(file.getAbsolutePath());
                        }

                    }

                }

            }

        }

        return result;

    }

    public static void main(String[] args) {

    }

}
