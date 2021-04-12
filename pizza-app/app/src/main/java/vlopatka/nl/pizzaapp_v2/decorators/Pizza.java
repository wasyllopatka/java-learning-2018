package vlopatka.nl.pizzaapp_v2.decorators;

import vlopatka.nl.pizzaapp_v2.utils.Constants;
import vlopatka.nl.pizzaapp_v2.decorators.toppings.ToppingModel;

import java.util.ArrayList;
import java.util.List;

public class Pizza implements IPizza {

    private double price;
    private String size;
    private String sauce;
    private List<ToppingModel> toppings = new ArrayList<>();

    private int currentSaucePosition = -1;

    public Pizza() {
        price = Constants.PIZZA_START_PRICE;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public double save(double price) {
        return this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public List<ToppingModel> getToppings() {
        return toppings;
    }

    public void setToppings(List<ToppingModel> toppings) {
        this.toppings = toppings;
    }

    public int getCurrentSaucePosition() {
        return currentSaucePosition;
    }

    public void setCurrentSaucePosition(int currentSaucePosition) {
        this.currentSaucePosition = currentSaucePosition;
    }
}
