import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarFactory {
    private final int[][] cars = {
            {30, -150}, {100, -1200}, {170, -450}, {350, -2000}, {370, -700}, {420, 200}};

    public List<Car> getRoadItems() {
        Random random = new Random();
        List<Car> items = new ArrayList<>();
        for (int[] cars : cars) {
            int randomChoice = random.nextInt(3);
            if (randomChoice == 1) {
                items.add(new Car(cars[0], cars[1], "icons/car1.png"));
            } else if (randomChoice == 2) {
                items.add(new Car(cars[0], cars[1], "icons/car2.png"));
            } else {
                items.add(new Car(cars[0], cars[1], "icons/car3.png"));
            }
        }
        return items;
    }
}