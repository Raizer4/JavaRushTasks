package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;

import static com.javarush.task.task30.task3008.MessageType.*;

public class Client {


    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    protected Connection connection;
    private volatile boolean clientConnected = false;


    protected String getServerAddress(){
       return ConsoleHelper.readString();
    }

    protected int getServerPort(){
        return ConsoleHelper.readInt();
    }

    protected String getUserName(){
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole(){
        return true;
    }

    protected SocketThread getSocketThread(){
        return new SocketThread();
    }

    protected void sendTextMessage(String text){
        try {
            connection.send(new Message(TEXT,text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Ошибка");
            clientConnected = false;
        }
    }

    public class SocketThread extends Thread {

        protected void clientHandshake() throws IOException, ClassNotFoundException {
                while (true) {
                    Message receive = connection.receive();
                    if (receive.getType() == NAME_REQUEST) {
                        String userName = getUserName();
                        connection.send(new Message(USER_NAME, userName));
                    } else if (receive.getType() == NAME_ACCEPTED) {
                        notifyConnectionStatusChanged(true);
                        return;
                    }else {
                        throw new IOException("Unexpected MessageType");
                    }
                }
        }

        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + " подключился к чату");
        }

        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + " покинул чат");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected){
           Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true){
                Message loop = connection.receive();
                if (loop.getType() == TEXT){
                    processIncomingMessage(loop.getData());
                }else if (loop.getType() == USER_ADDED){
                    informAboutAddingNewUser(loop.getData());
                }else if (loop.getType() == USER_REMOVED){
                    informAboutDeletingNewUser(loop.getData());
                }else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        

    }

    public void run(){
        try {
            synchronized (this) {
                SocketThread socketThread = getSocketThread();
                socketThread.setDaemon(true);
                socketThread.start();
                this.wait();

                if (clientConnected == false){
                    ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
                }

                while (clientConnected){
                    ConsoleHelper.writeMessage("Соединение установлено.");
                    String text = ConsoleHelper.readString();
                    if (text.equals("exit")){
                        break;
                    }
                    Client client = new Client();
                    if (client.shouldSendTextFromConsole()){
                        client.sendTextMessage(text);
                    }

                }

            }
        } catch (InterruptedException e) {
            ConsoleHelper.writeMessage("Ошибка");
            return;
        }
    }

}
