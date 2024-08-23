package com.javarush.task.task26.task2613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString(){
        try {
            return bis.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String askCurrencyCode() {
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

    public static String[] getValidTwoDigits(String currencyCode) {
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

    public static Operation askOperation() {
        while (true) {
            try {
                writeMessage("Выбирите действие: 1-Info, 2-Deposit, 3-Withdraw, 4-Exit");
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
            } catch (Exception e) {
                writeMessage("Не правильное введенное значение");
            }
        }
    }

}
