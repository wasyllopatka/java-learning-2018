package vlopatka.nl.pizzaapp_v2.decorators.sauces;

import vlopatka.nl.pizzaapp_v2.utils.Constants;
import vlopatka.nl.pizzaapp_v2.decorators.IPizza;
import vlopatka.nl.pizzaapp_v2.decorators.Sauce;

public class TomatoSauce extends Sauce {

    public TomatoSauce(IPizza IPizza) {
        this.IPizza = IPizza;
    }

    @Override
    public double getPrice() {
        return IPizza.getPrice() + Constants.TOMATO_SAUCE_PRICE;
    }
}
