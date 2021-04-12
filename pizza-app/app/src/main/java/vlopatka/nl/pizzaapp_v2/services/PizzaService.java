package vlopatka.nl.pizzaapp_v2.services;

import android.annotation.SuppressLint;
import vlopatka.nl.pizzaapp_v2.decorators.Pizza;
import vlopatka.nl.pizzaapp_v2.fragments.ProgressFragment;

public class PizzaService {
    public static Pizza pizza = new Pizza();

    /** Save new price of pizza and set total amount
       of static variable in Progress fragment
    */
    @SuppressLint("DefaultLocale")
    public void setAmount(double newPrice) {
        ProgressFragment.totalAmount.setText(String.format("%1$,.2f", pizza.save(newPrice)));
    }

    /** Reduce and save the price of pizza when the previous item is not selected
       and set to total amount of static variable in Progress fragment
    */
    @SuppressLint("DefaultLocale")
    public void reducePrice(double previousItemPrice) {
        ProgressFragment.totalAmount.setText(String.format("%1$,.2f", pizza.save(pizza.getPrice() - previousItemPrice)));
    }

    // Get current pizza price
    public double getAmount() {
        return pizza.getPrice();
    }

}
