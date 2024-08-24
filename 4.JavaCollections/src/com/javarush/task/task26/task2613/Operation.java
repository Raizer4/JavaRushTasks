package com.javarush.task.task26.task2613;



public enum Operation {
    LOGIN,INFO, DEPOSIT, WITHDRAW, EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i){
        try {
            Operation[] values = Operation.values();
            if (i == 0){
                throw new IllegalArgumentException();
            }
            Operation value = values[i];
            return value;
        }catch (Exception e){
            throw new IllegalArgumentException();
        }
    }

}
