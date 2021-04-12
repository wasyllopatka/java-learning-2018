import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelOne extends JPanel implements IChecker, Runnable {
    private ImageIcon icon;
    private Froggy froggy;
    private List<MovingItem> items = new ArrayList<>();
    private static int delay;
    private static volatile boolean running;
    private static int lives = 3;
    private Thread thread;

    public LevelOne() {
        running = true;
        loadMap();
        initPanel();
        items = new ItemFactory().getRoadItems();

    }

    private void initPanel() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        froggy = new Froggy(270, 435, 1);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        thread = new Thread(this);
        thread.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        icon.paintIcon(this, g, 0, 0);
        drawObjects(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {
        g.drawImage(froggy.getImage(), froggy.getX(), froggy.getY(), this);
        for (MovingItem movingItem : items) {
            g.drawImage(movingItem.getImage(), movingItem.getX(), movingItem.getY(), this);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier", Font.BOLD, 20));
        g.drawString("Lives: " + lives, 100, 30);
        g.drawString("LEVEL:  1", 400, 30);
    }

    private void loadMap() {
        icon = new ImageIcon("img/map1.png");
    }

    private void updateFrog() {
        froggy.move();
    }

    private void updateItems() {
        for (MovingItem item : items) {
            item.move();
        }
    }

    @Override
    public void checkCollisions() {
        Rectangle froggyRec = froggy.getBounds();
        if (froggy.getX() > 520) froggy.setX(520);
        if (froggy.getY() > 435) froggy.setY(435);
        for (MovingItem movingItem : items) {
            Rectangle itemRec = movingItem.getBounds();
            if (froggyRec.intersects(itemRec)) {
                froggy = new Froggy(270, 435, 1);
                if (getLives() < 1) {
                    setGameOver();
                } else if (getLives() >= 1) {
                    lives--;
                    froggy = new Froggy(270, 435, 1);
                }
            }
        }
    }

    @Override
    public void checkSpriteIntersects() {
        for (int i = 0; i < items.size(); i++) {
            for (int k = i + 1; k < items.size(); k++) {
                Rectangle item1 = items.get(i).getBounds();
                Rectangle item2 = items.get(k).getBounds();
                if (item1.intersects(item2)) {
                    Random ran = new Random();
                    int random = ran.nextInt(125) + 50;
                    items.get(k).setX(items.get(k).getX() - random);
                }
            }
        }
    }

    @Override
    public void checkIfWin() {
        Rectangle winRec = new Rectangle(0, 0, 600, 30);
        Rectangle froggyRec = froggy.getBounds();
        if (froggyRec.intersects(winRec)) {
            Object[] options = {"Yes", "No!"};
            int choice = JOptionPane.showOptionDialog(getParent(), "Next level?",
                    "YOU WIN", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options,
                    options[0]);
            if (choice == 0) {
                Game.setIsLevelOne(false);
                terminate();
                Game.setIsLevelTwo(true);
                LevelTwo.setRunning(true);
                LevelTwo.setDelay(getDelay());
                Game.setNewLifeL2();
                Game.panelSettings();
            } else if (choice == 1) {
                Game.setIsLevelOne(false);
                Game.setIsMenu(true);
                Game.panelSettings();
            }
            terminate();
            froggy = new Froggy(220, 435, 1);
        }
    }

    @Override
    public void run() {
        while (running) {
            updateFrog();
            updateItems();
            checkSpriteIntersects();
            checkCollisions();
            checkIfWin();
            repaint();
            try {
                Thread.sleep(getDelay());
            } catch (InterruptedException e) {
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void setGameOver() {
        terminate();
        Game.setIsLastLevelOne(true);
        Game.setIsLevelOne(false);
        Game.setIsGameOver(true);
        Game.panelSettings();
    }

    public static int getDelay() {
        return delay;
    }

    public static void setDelay(int delay) {
        LevelOne.delay = delay;
    }

    public static void terminate() {
        running = false;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void checkPosition() {
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            froggy.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            froggy.keyPressed(e);
        }
    }

}
