package com.javarush.task.task36.task3609;

/* 
Рефакторинг MVC
*/

public class Solution {

    public static void main(String[] args) {

        CarModel model = retrieveCarFromDatabase();
        SpeedometerView view = new SpeedometerView();
        CarController controller = new CarController(model, view);

        controller.updateView();

        model.increaseSpeed(15);
        controller.updateView();

        model.increaseSpeed(50);
        controller.updateView();

        model.decreaseSpeed(7);
        controller.updateView();
    }

    private static CarModel retrieveCarFromDatabase() {
        CarModel currentCar = new CarModel();
        currentCar.setBrand("Nissan");
        currentCar.setModel("Almera Classic");
        currentCar.setSpeed(0);
        currentCar.setMaxSpeed(200);
        return currentCar;
    }

}