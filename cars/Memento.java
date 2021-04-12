public class Memento {
    private int x;
    private int y;
    private int fuel;
    private int distance;

    public Memento(int x, int y, int fuel, int distance) {
        this.x = x;
        this.y = y;
        this.fuel = fuel;
        this.distance = distance;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getFuel() {
        return fuel;
    }

    public int getDistance() {
        return distance;
    }
}
