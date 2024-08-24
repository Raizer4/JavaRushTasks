package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String line = bis.readLine();
            if ("exit".equals(line.toLowerCase())) {
                throw new InterruptOperationException();
            }
            return line;
        } catch (IOException e) {
        }
        return null;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        while (true) {
            writeMessage("Введите код валюты");
            String currencyCode = readString().toUpperCase();
            if (currencyCode.length() == 3){
                return currencyCode;
            }else {
                writeMessage("Значение не верное");
            }
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while (true) {
            writeMessage("Введите два целых положительных числа: Номинал и Количество банкнот");
            String[] s = readString().split(" ");

            try {
                int temp_1 = Integer.parseInt(s[0]);
                int temp_2 = Integer.parseInt(s[1]);

                if (s.length == 2 && temp_1 > 0 && temp_2 > 0) {
                    return s;
                } else {
                    writeMessage("Значение не верное");
                }

            } catch (Exception e) {
                System.out.println("Ошибка: Неправильный формат числа");
            }


        }
    }

    public static Operation askOperation() throws InterruptOperationException{
        while (true) {
            try {
                writeMessage("Выбирите действие: 1-Info, 2-Deposit, 3-Withdraw, 4-Exit");
                String string = readString();
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(string));
            }catch (IndexOutOfBoundsException e){
                writeMessage("Не правильное введенное значение");
            }

        }
    }

}
