package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;

import java.nio.file.Paths;
import java.util.Scanner;

public class Archiver {
    public static void main(String[] args) throws Exception {

        String line = new Scanner(System.in).nextLine();
        ZipFileManager zipFileManager = new ZipFileManager(Paths.get(line));

        String archives = new Scanner(System.in).nextLine();
        zipFileManager.createZip(Paths.get(archives));

        ExitCommand exit = new ExitCommand();
        exit.execute();

    }
}
