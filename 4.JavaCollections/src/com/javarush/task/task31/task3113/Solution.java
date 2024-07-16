package com.javarush.task.task31.task3113;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/* 
Что внутри папки?
*/

public class Solution extends SimpleFileVisitor<Path>{

    public static void main(String[] args) throws IOException {

        String line = new Scanner(System.in).nextLine();
        Path path = Paths.get(line);

        if (!Files.isDirectory(path)){
            System.out.println(path.toString() + " - не папка.");
        }else {

            final Solution solution = new Solution();

            Files.walkFileTree(path, solution);

            int countByte = 0;

            for (Path temp : solution.regularFile) {
                byte[] bytes = Files.readAllBytes(temp);
                countByte += bytes.length;
            }

            System.out.println("Всего папок - " + (solution.directory.size() - 1));
            System.out.println("Всего файлов - " + solution.regularFile.size());
            System.out.println("Общий размер - " + countByte);
        }
    }

    List<Path> directory = new ArrayList<>();
    List<Path> regularFile = new ArrayList<>();

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        directory.add(dir);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isRegularFile(file)) {
            regularFile.add(file);
        }
        return FileVisitResult.CONTINUE;
    }






}
