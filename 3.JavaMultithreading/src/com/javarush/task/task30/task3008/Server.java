package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

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
        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {


            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message name = connection.receive();

                if (name.getType() != MessageType.USER_NAME) {
                    continue;
                }else if (name.getType() == MessageType.USER_NAME && !name.getData().isEmpty() && name.getData() != null) {
                    if (!connectionMap.containsKey(name.getData())) {
                        connectionMap.put(name.getData(), connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        ConsoleHelper.writeMessage(name.getData()+ " принято");
                        return name.getData();
                    }
                }
            }

        }
        private void notifyUsers(Connection connection, String userName) throws IOException {

            for (String temp : connectionMap.keySet()){
                Connection connectionFromBd = connectionMap.get(temp);
                if (temp != userName){
                    connection.send(new Message(MessageType.USER_ADDED,temp));
                }
            }

        }
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {

            while (true){

                Message receive = connection.receive();

                if (receive.getType() == MessageType.TEXT){
                    sendBroadcastMessage(new Message(MessageType.TEXT,userName + ": " + receive.getData()));
                } else{
                    ConsoleHelper.writeMessage("Ошибка");
                }

            }


        }

        public void run(){
            ConsoleHelper.writeMessage("Было установлено соединение с удаленным адресом "+socket.getRemoteSocketAddress());

            try(Connection connection = new Connection(socket)) {
                String userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED,userName));
                notifyUsers(connection,userName);
                serverMainLoop(connection,userName);
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED,userName));
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Получено сообщение от " + socket.getRemoteSocketAddress() + ". Тип сообщения не соответствует протоколу.");
            } catch (ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Получено сообщение от " + socket.getRemoteSocketAddress() + ". Тип сообщения не соответствует протоколу.");
            }
            ConsoleHelper.writeMessage("Cоединение с удаленным адресом закрыто");

        }

    }

    public static void sendBroadcastMessage(Message message) {
        try {
            for (String temp : connectionMap.keySet()) {
                Connection connection = connectionMap.get(temp);
                connection.send(message);
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Ошибка не смогли отправить сообщение");
        }

    }

}
