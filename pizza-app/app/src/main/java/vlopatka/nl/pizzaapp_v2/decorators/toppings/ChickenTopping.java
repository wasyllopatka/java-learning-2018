package vlopatka.nl.pizzaapp_v2.decorators.toppings;

import vlopatka.nl.pizzaapp_v2.utils.Constants;
import vlopatka.nl.pizzaapp_v2.decorators.IPizza;
import vlopatka.nl.pizzaapp_v2.decorators.Topping;

public class ChickenTopping extends Topping {

    public ChickenTopping(IPizza IPizza) {
        this.IPizza = IPizza;
    }

    @Override
    public double getPrice() {
        return IPizza.getPrice() + Constants.CHICKEN_PRICE;
    }

    @Override
    public double removePrice() {
        return IPizza.getPrice() - Constants.CHICKEN_PRICE;
    }
}
