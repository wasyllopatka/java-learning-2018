package vlopatka.nl.pizzaapp_v2.decorators.toppings;

import vlopatka.nl.pizzaapp_v2.utils.Constants;
import vlopatka.nl.pizzaapp_v2.decorators.IPizza;
import vlopatka.nl.pizzaapp_v2.decorators.Topping;

public class UiTopping extends Topping {

    public UiTopping(IPizza IPizza) {
        this.IPizza = IPizza;
    }

    @Override
    public double getPrice() {
        return IPizza.getPrice() + Constants.UI_PRICE;
    }

    @Override
    public double removePrice() {
        return IPizza.getPrice() - Constants.UI_PRICE;
    }
}
