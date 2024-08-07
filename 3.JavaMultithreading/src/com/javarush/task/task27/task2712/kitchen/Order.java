
package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {

    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }

    public int getTotalCookingTime() {
        int res = 0;

        for (Dish temp : dishes) {
            res += temp.getDuration();
        }

        return res;
    }

    public boolean isEmpty(){
       return dishes.isEmpty();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }

    protected void initDishes() throws IOException {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }



    @Override
    public String toString() {
        return "Your order: " +
                dishes +
                " of " + tablet +
                ", cooking time " + getTotalCookingTime() + "min";
    }

}
