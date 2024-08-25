package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit");
    @Override
    public void execute() throws InterruptOperationException {
       try {
           ConsoleHelper.writeMessage(res.getString("before"));
           String currencyCode = ConsoleHelper.askCurrencyCode();
           String[] validTwoDigits = ConsoleHelper.getValidTwoDigits(currencyCode);

           int denomination = Integer.parseInt(validTwoDigits[0]);
           int count = Integer.parseInt(validTwoDigits[1]);

           CurrencyManipulator manipulatorByCurrencyCode = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
           manipulatorByCurrencyCode.addAmount(denomination, count);
           ConsoleHelper.writeMessage(String.format(res.getString("success.format"),(denomination * count), currencyCode));
       } catch (NumberFormatException e){
           ConsoleHelper.writeMessage(res.getString("invalid.data"));
       }
    }

}
