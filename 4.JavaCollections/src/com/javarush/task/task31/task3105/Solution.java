package com.javarush.task.task31.task3105;

import javax.xml.crypto.dsig.spec.XPathType;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/

public class Solution {

    public static void main(String[] args) {
        String fileName = args[0];
        String zipArchivePath = args[1];


        try (ZipInputStream input = new ZipInputStream(Files.newInputStream(Paths.get(args[1])));
             ZipOutputStream output = new ZipOutputStream(Files.newOutputStream(Paths.get(args[1])))
        ) {

            Path filePath = Paths.get(fileName);
            Path directory = Paths.get("new/");
            Path resolve = directory.resolve(filePath);
            Files.copy(resolve,output);

            ZipEntry nextEntry = input.getNextEntry();

            while (nextEntry != null) {
                ZipEntry newEntry = new ZipEntry(nextEntry.getName());
                output.putNextEntry(newEntry);
                copyData(input, output);
                output.closeEntry();
                nextEntry = input.getNextEntry();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }




    private static void copyData(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }

}
