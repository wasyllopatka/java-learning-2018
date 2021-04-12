import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemFactory {

    private final int[][] rightDirectionRoad = {
            {20, 140}, {150, 140}, {350, 140}, {500, 140},
            {0, 370}, {140, 370}, {260, 370}, {400, 370}};

    private final int[][] leftDirectionRoad = {
            {50, 70}, {200, 70}, {350, 70}, {490, 70},
            {20, 280}, {170, 280}, {320, 280}, {500, 280},
    };

    private final int[][] rightDirectionRiver = {
            {20, 100}, {150, 100}, {350, 100}, {500, 100},
            {20, 240}, {150, 240}, {350, 240}, {500, 240},
            {0, 370}, {190, 370}, {260, 370}, {400, 370}};

    private final int[][] leftDirectionRiver = {
            {50, 170}, {200, 170}, {350, 170}, {490, 170},
            {20, 300}, {170, 300}, {320, 300}, {500, 300},
    };

    public List<MovingItem> getRoadItems() {
        Random random = new Random();
        List<MovingItem> items = new ArrayList<>();
        for (int[] right : rightDirectionRoad) {
            int randomChoice = random.nextInt(2);
            if (randomChoice == 1) {
                items.add(new MovingItem(right[0], right[1], 1, "img/car.png"));
            } else {
                items.add(new MovingItem(right[0], right[1], 1, "img/truck.png"));
            }
        }
        for (int[] left : leftDirectionRoad) {
            int randomC = random.nextInt(2);
            if (randomC == 1) {
                items.add(new MovingItem(left[0], left[1], -1, "img/retro-car.png"));
            } else {
                items.add(new MovingItem(left[0], left[1], -1, "img/sport-car.png"));
            }
        }
        return items;
    }

    public List<MovingItem> getRiverItems() {
        Random random = new Random();
        List<MovingItem> items = new ArrayList<>();
        for (int[] right : rightDirectionRiver) {
            int randomChoice = random.nextInt(2);
            if (randomChoice == 1) {
                items.add(new MovingItem(right[0], right[1], 1, "img/log.png"));
            } else {
                items.add(new MovingItem(right[0], right[1], 1, "img/turtle.png"));
            }
        }
        for (int[] left : leftDirectionRiver) {
            int randomC = random.nextInt(2);
            if (randomC == 1) {
                items.add(new MovingItem(left[0], left[1], -1, "img/log.png"));
            } else {
                items.add(new MovingItem(left[0], left[1], -1, "img/turtle.png"));
            }
        }
        return items;
    }
}