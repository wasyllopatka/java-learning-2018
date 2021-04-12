import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Car {
    private int x;
    private int y;
    private int stepX;
    private int stepY;
    private int fuel;
    private int distance = 0;
    private boolean visible = true;
    private String link;
    private BufferedImage image;
    private RaceArena arena;

    public Car(int x, int y, String link) {
        this.x = x;
        this.y = y;
        this.link = link;
        setCarIcon(link);
    }

    public Car(int x, int y, int fuel, String link, RaceArena arena) {
        this.x = x;
        this.y = y;
        this.fuel = fuel;
        this.link = link;
        this.arena = arena;
        setCarIcon(link);
    }

    public Memento save() {
        return new Memento(x, y, fuel, distance);
    }

    public void loadMemento(Memento memento) {
        x = memento.getX();
        y = memento.getY();
        fuel = memento.getFuel();
        distance = memento.getDistance();
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    public void move() {
        x += stepX;
        y += stepY;
        if (x < 10) x = 10;
        if (y < 10) y = 10;
        if (x > 440) x = 440;
        if (y > 575) y = 575;
    }

    public void moveRandom() {
        Random ran = new Random();
        int random = ran.nextInt(20) + 100;
        y += 4;
        if (y > 700) y = -1 * random;
    }

    private void setCarIcon(String link) {
        try {
            image = ImageIO.read(this.getClass().getResource(link));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawCar(Graphics2D g) {
        g.drawImage(image, x, y, null);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) stepX = -5;
        if (key == KeyEvent.VK_ESCAPE)
            if (!arena.isPaused()) {
                arena.pause();
            } else arena.start();
        if (key == KeyEvent.VK_RIGHT) stepX = 5;
        if (key == KeyEvent.VK_UP) {
            stepY = -5;
            arena.start();
        }
        if (key == KeyEvent.VK_DOWN) stepY = 5;
        if (arena.isPaused()) {
            if (key == KeyEvent.VK_Q) System.exit(0);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) stepX = 0;
        if (key == KeyEvent.VK_RIGHT) stepX = 0;
        if (key == KeyEvent.VK_UP) stepY = 0;
        if (key == KeyEvent.VK_DOWN) stepY = 0;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getLink() {
        return link;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
