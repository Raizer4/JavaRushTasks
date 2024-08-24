package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

class WithdrawCommand implements Command {

    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulatorByCurrencyCode = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        while (true){
            ConsoleHelper.writeMessage("Введите сумму");

            try {
                int amount = Integer.parseInt(ConsoleHelper.readString());

                if (amount <= 0){
                    ConsoleHelper.writeMessage("Не верное введеное значение");
                    continue;
                }

                if (!manipulatorByCurrencyCode.isAmountAvailable(amount)){
                    continue;
                }

                Map<Integer, Integer> integerIntegerMap = manipulatorByCurrencyCode.withdrawAmount(amount);

                ArrayList<Integer> keys = new ArrayList<>(integerIntegerMap.keySet());
                keys.sort(Collections.reverseOrder());

                for (Integer temp : keys){
                    ConsoleHelper.writeMessage("\t" + temp + " - " + integerIntegerMap.get(temp));
                }

                ConsoleHelper.writeMessage("Транзакция успешно выполнена.");
                break;
            }catch (NumberFormatException e){
                ConsoleHelper.writeMessage("Не верное введеное значение");
            }catch (NotEnoughMoneyException e){
                ConsoleHelper.writeMessage("Не хватает банкнот");
            }

        }

    }

}
