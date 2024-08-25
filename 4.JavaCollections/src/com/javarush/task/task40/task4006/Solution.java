package com.javarush.task.task40.task4006;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        String server = url.getHost();
        String path = url.getPath();

        try (Socket socket = new Socket(server, 80);
             PrintStream out = new PrintStream(socket.getOutputStream());
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println("GET " + path);
            out.println();

            String line = in.readLine();
            while (line != null) {
                System.out.println(line);
                line = in.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}