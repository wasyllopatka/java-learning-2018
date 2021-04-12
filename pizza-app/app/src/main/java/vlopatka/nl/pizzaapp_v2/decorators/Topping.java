package vlopatka.nl.pizzaapp_v2.decorators;

public abstract class Topping implements IPizza {
    public IPizza IPizza;

    abstract public double getPrice();

    abstract public double removePrice();
}
