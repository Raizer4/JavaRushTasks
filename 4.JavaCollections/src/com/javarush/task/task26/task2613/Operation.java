package com.javarush.task.task26.task2613;



public enum Operation {
    INFO, DEPOSIT, WITHDRAW, EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i){
        try {
            Operation[] values = Operation.values();
            Operation value = values[i - 1];
            return value;
        }catch (Exception e){
            throw new IllegalArgumentException();
        }
    }

}
