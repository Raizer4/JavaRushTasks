package com.javarush.task.task26.task2613;





import java.io.Console;
import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);

        String currencyCode = ConsoleHelper.askCurrencyCode();
        String[] validTwoDigits = ConsoleHelper.getValidTwoDigits(currencyCode);

        int denomination = Integer.parseInt(validTwoDigits[0]);
        int count = Integer.parseInt(validTwoDigits[1]);

        CurrencyManipulator manipulatorByCurrencyCode = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        manipulatorByCurrencyCode.addAmount(denomination,count);
        ConsoleHelper.writeMessage(String.valueOf(manipulatorByCurrencyCode.getTotalAmount()));
    }
}
