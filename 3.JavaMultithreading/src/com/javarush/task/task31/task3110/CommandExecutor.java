package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.Command;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandExecutor {

    private static Map<Operation, Command> ALL_KNOWN_COMMANDS_MAP = new ConcurrentHashMap<>();



    private CommandExecutor() {
    }




}
