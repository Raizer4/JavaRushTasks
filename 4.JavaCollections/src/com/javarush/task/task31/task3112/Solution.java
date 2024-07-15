package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Загрузчик файлов
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }

    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url = new URL(urlString);

        try (InputStream inputStream = url.openStream()) {
            Path tempFile = Files.createTempFile("temp-", ".tmp");
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);


            Pattern pattern = Pattern.compile(".*/([^/?#]+\\.[^/?#]+)(?:\\?.*)?$");
            Matcher matcher = pattern.matcher(urlString);

            String fileName;

            if (matcher.find()) {
                fileName = matcher.group(1);
            } else {
                throw new IOException("Invalid URL: cannot extract file name.");
            }


            Path destinationFile = downloadDirectory.resolve(fileName);
            return Files.move(tempFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);
    }
}

}
