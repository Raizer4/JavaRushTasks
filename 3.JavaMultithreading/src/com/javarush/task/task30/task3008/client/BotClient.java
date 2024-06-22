package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;

import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.javarush.task.task30.task3008.MessageType.*;

public class BotClient extends Client {

    @Override
    protected SocketThread getSocketThread(){
        return new BotSocketThread();
    }
    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }
    @Override
    protected String getUserName(){
        String text = "date_bot_";
        int random = (int)(Math.random() * 100);
        return text + random;
    }

    public static void main(String[] args) {
        BotClient client = new BotClient();
        client.run();
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
            if (!message.contains(": ")) return;
            String[] array = message.split(": ");

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat;
            String response;

            switch (array[1].toLowerCase()) {
                case "дата":
                    dateFormat = new SimpleDateFormat("d.MM.YYYY");
                    response = dateFormat.format(calendar.getTime());
                    break;
                case "день":
                    dateFormat = new SimpleDateFormat("d");
                    response = dateFormat.format(calendar.getTime());
                    break;
                case "месяц":
                    dateFormat = new SimpleDateFormat("MMMM");
                    response = dateFormat.format(calendar.getTime());
                    break;
                case "год":
                    dateFormat = new SimpleDateFormat("YYYY");
                    response = dateFormat.format(calendar.getTime());
                    break;
                case "время":
                    dateFormat = new SimpleDateFormat("H:mm:ss");
                    response = dateFormat.format(calendar.getTime());
                    break;
                case "час":
                    dateFormat = new SimpleDateFormat("H");
                    response = dateFormat.format(calendar.getTime());
                    break;
                case "минуты":
                    dateFormat = new SimpleDateFormat("m");
                    response = dateFormat.format(calendar.getTime());
                    break;
                case "секунды":
                    dateFormat = new SimpleDateFormat("s");
                    response = dateFormat.format(calendar.getTime());
                    break;
                default:
                    response = "Unknown request";
                    return;
            }



            sendTextMessage("Информация для " + array[0]  + ": " + response);

        }

    }



}
