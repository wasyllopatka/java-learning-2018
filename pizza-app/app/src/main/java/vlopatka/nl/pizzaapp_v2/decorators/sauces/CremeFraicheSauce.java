package vlopatka.nl.pizzaapp_v2.decorators.sauces;

import vlopatka.nl.pizzaapp_v2.utils.Constants;
import vlopatka.nl.pizzaapp_v2.decorators.IPizza;
import vlopatka.nl.pizzaapp_v2.decorators.Sauce;

public class CremeFraicheSauce extends Sauce {

    public CremeFraicheSauce(IPizza IPizza) {
        this.IPizza = IPizza;
    }

    @Override
    public double getPrice() {
        return IPizza.getPrice() + Constants.CREME_FRAICHE_SAUCE_PRICE;
    }
}
