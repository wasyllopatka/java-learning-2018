import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class RaceArena extends JPanel {
    private Road road;
    private Car car;
    private List<Car> items = new ArrayList<>();
    private Caretaker caretaker;
    public Future<?> running = null;
    private int collisions;
    private boolean paused = false;
    private boolean crashed = false;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public RaceArena() {
        initArena();
    }

    private void initArena() {
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);
        road = new Road();
        car = new Car(230, 550, 10000, "icons/car.png", this);
        items = new CarFactory().getRoadItems();
        caretaker = new Caretaker();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawObjects(g2d);
        Toolkit.getDefaultToolkit().sync();
    }

    public void race() {
        car.move();
        car.setFuel(car.getFuel() - 1);
        car.setDistance(car.getDistance() + 1);
        for (Car car : items) {
            car.moveRandom();
        }
        checkCollision();
        checkSpriteIntersects();
        resumeAndBack();
    }

    public void start() {
        if (running != null) return;
        running = executor.submit(new RaceThread(this));
        repaint();
        collisions = 0;
        paused = false;
        crashed = false;
        car.setY(500);
    }

    public void pause() {
        if (running == null) return;
        running.cancel(false);
        running = null;
        paused = true;
        crashed = false;
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(car.save());
        car.loadMemento(caretaker.getMemento());
        repaint();
    }

    private void resumeAndBack() {
        if (collisions == 3) {
            running.cancel(false);
            running = null;
            crashed = true;
            Caretaker caretaker = new Caretaker();
            caretaker.setMemento(car.save());
            car.loadMemento(caretaker.getMemento());
            car.setX(230);
            car.setY(530);
            repaint();
        }
    }

    private void checkCollision() {
        Iterator<Car> i = items.iterator();
        while (i.hasNext()) {
            Car item = i.next();
            if (car.getBounds().intersects(item.getBounds())) {
                car.setX(250);
                collisions++;
                break;
            }
        }
    }

    private void checkSpriteIntersects() {
        for (int i = 0; i < items.size(); i++) {
            for (int k = i + 1; k < items.size(); k++) {
                Rectangle item1 = items.get(i).getBounds();
                Rectangle item2 = items.get(k).getBounds();
                if (item1.intersects(item2) ||
                        ((item1.getY() > item2.getY() - 50)) && (item1.getY() < item2.getY() + 50)) {
                    Random ran = new Random();
                    int random = ran.nextInt(1200) + 200;
                    items.get(k).setY(items.get(k).getY() - random);
                }
            }
        }
    }

    private void drawObjects(Graphics2D g) {
        getRoad().drawRoad(g);
        getRoad().drawRoad(g);
        car.drawCar(g);
        for (Car item : items) {
            item.drawCar(g);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier", Font.BOLD, 20));
        g.drawString("FUEL: ", 510, 50);
        g.setFont(new Font("Courier", Font.BOLD, 24));
        g.drawString("" + car.getFuel(), 580, 50);
        g.setFont(new Font("Courier", Font.BOLD, 20));
        g.drawString("COLLISIONS: " + collisions, 510, 90);
        g.drawString("Distance: " + car.getDistance(), 510, 130);
        g.drawString("CONTROLS: ", 510, 510);
        g.setFont(new Font("Courier", Font.BOLD, 17));
        g.setColor(Color.orange);
        g.drawString("<UP> to start ", 510, 540);
        g.drawString("<ESC> to pause ", 510, 570);
        g.drawString("<LEFT> to go left ", 510, 600);
        g.drawString("<RIGHT> to go right ", 510, 630);
        if (isPaused()) {
            g.drawString("PAUSE", 20, 20);
            g.drawString("Press <ESC> of <UP> to continue", 20, 40);
            g.drawString("Press <Q> to exit", 20, 60);
        }

        if (isCrashed()) {
            g.setFont(new Font("Courier", Font.BOLD, 30));
            g.setColor(Color.RED);
            g.drawString("CRASHED 3 TIMES", 150, 250);
            g.setColor(Color.orange);
            g.setFont(new Font("Courier", Font.BOLD, 15));
            g.drawString("Press <UP> arrow key to continue", 150, 270);

        }
    }

    public Road getRoad() {
        return road;
    }

    public Caretaker getCaretaker() {
        return caretaker;
    }

    public boolean isPaused() {
        return paused;
    }

    public boolean isCrashed() {
        return crashed;
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            car.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            car.keyPressed(e);
        }
    }
}
