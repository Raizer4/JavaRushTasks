package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Server {

    private static Map<String, Connection> connectionMap;

    public static void main(String[] args) throws IOException {

        int port = ConsoleHelper.readInt();
        try (ServerSocket socket = new ServerSocket(port)) {
            ConsoleHelper.writeMessage("Сервер запущен");

            while (true) {
                Socket accept = socket.accept();
                Handler handler = new Handler(accept);
                handler.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

}

    private static class Handler extends Thread {
        private Socket socket;
        public Handler(Socket socket){
            this.socket = socket;
        }

    }

}
