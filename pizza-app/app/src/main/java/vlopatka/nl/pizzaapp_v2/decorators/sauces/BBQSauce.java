package vlopatka.nl.pizzaapp_v2.decorators.sauces;

import vlopatka.nl.pizzaapp_v2.utils.Constants;
import vlopatka.nl.pizzaapp_v2.decorators.IPizza;
import vlopatka.nl.pizzaapp_v2.decorators.Sauce;

public class BBQSauce extends Sauce {

    public BBQSauce(IPizza IPizza) {
        this.IPizza = IPizza;
    }

    @Override
    public double getPrice() {
        return IPizza.getPrice() + Constants.BBQ_SAUCE_PRICE;
    }
}
