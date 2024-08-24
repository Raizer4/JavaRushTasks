package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards");

    @Override
    public void execute() throws InterruptOperationException {
        while(true){
            ConsoleHelper.writeMessage("Введите номер вашей карты и пин");

            String creditCardNumber = ConsoleHelper.readString();
            String pinStr = ConsoleHelper.readString();

            if (creditCardNumber.length() != 12 || pinStr.length() != 4){
                ConsoleHelper.writeMessage("Данные не валидны");
                continue;
            }

            if (validCreditCards.containsKey(creditCardNumber) && pinStr.equals(validCreditCards.getString(creditCardNumber))){
                ConsoleHelper.writeMessage("Все верно");
                break;
            }else {
                ConsoleHelper.writeMessage("Данные не верны");
                continue;
            }

        }
    }

}
