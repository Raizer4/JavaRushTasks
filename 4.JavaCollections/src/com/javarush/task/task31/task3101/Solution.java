package com.javarush.task.task31.task3101;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/* 
Проход по дереву файлов
*/

public class Solution {

    public static void main(String[] args) {

        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);

        File dest = new File(resultFileAbsolutePath.getParentFile() + "/allFilesContent.txt");
        if (FileUtils.isExist(dest)) {
            FileUtils.deleteFile(dest);
        }
        FileUtils.renameFile(resultFileAbsolutePath, dest);

        try (FileOutputStream fos = new FileOutputStream(dest)) {
            Queue<File> queue = new LinkedList<>();

            if (path.exists() && path.isDirectory()) {
                queue.add(path);

                while (!queue.isEmpty()) {
                    File currentDir = queue.poll();
                    File[] files = currentDir.listFiles();

                    if (files != null) {

                        for (File file : files) {
                            if (file.isDirectory()) {
                                queue.add(file);
                            } else {
                                try(FileInputStream in = new FileInputStream(file)) {

                                    if (file.length() <= 50) {
                                        byte[] buffer = new byte[8 * 1024];
                                        int len;

                                        while ((len = in.read(buffer)) > 0) {
                                            fos.write(buffer, 0, len);
                                        }
                                        fos.write("\n".getBytes(StandardCharsets.UTF_8));
                                    }

                                }
                            }
                        }

                    }

                }

            }


        }
        catch (Exception e) {
            System.out.println("Ошибка");
        }

    }

}