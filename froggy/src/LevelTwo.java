import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelTwo extends JPanel implements IChecker, Runnable {
    private ImageIcon icon;
    private Froggy froggy;
    private List<MovingItem> items = new ArrayList<>();
    private static int delay;
    private static volatile boolean running;
    private static int lives = 3;
    private Thread thread;

    public LevelTwo() {
        running = true;
        loadMap();
        initPanel();
        items = new ItemFactory().getRiverItems();
    }

    private void initPanel() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        froggy = new Froggy(270, 430, 1);
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
        for (MovingItem item : items) {
            g.drawImage(item.getImage(), item.getX(), item.getY(), this);
        }
        g.setColor(Color.blue);
        g.setFont(new Font("Courier", Font.BOLD, 20));
        g.drawString("Lives: " + lives, 100, 60);
        g.drawString("LEVEL:  2", 400, 60);
    }

    private void loadMap() {
        icon = new ImageIcon("img/map2.png");
    }

    private void updateFrog() {
        froggy.move();
    }

    private void updateItems() {
        for (int i = 0; i < items.size(); i++) {
            MovingItem item = items.get(i);
            item.move();
        }
    }

    @Override
    public void checkPosition() {
        Rectangle froggyRec = froggy.getBounds();
        Rectangle waterRec = new Rectangle(0, 100, 600, 280);
        if (froggy.getY() > 437) froggy.setY(415);
        for (MovingItem item : items) {
            Rectangle itemRec = item.getBounds();
            if (froggyRec.intersects(itemRec)) {
                froggy.setX(froggy.getX() + item.direction);
            }
        }
        if (froggy.getX() >= 590) {
            if (lives > 0) {
                takeFroggyLife();
            } else {
                setGameOver();
            }
        }
        if (froggy.getX() == 0) {
            if (lives > 0) {
                takeFroggyLife();
            } else {
                setGameOver();
            }
        }
        boolean swimming = false;
        if (froggyRec.intersects(waterRec)) {
            for (MovingItem item : items) {
                Rectangle itemRec = new Rectangle(item.getBounds());
                if (froggyRec.intersects(itemRec)) {
                    swimming = true;
                    break;
                } else {
                    swimming = false;
                }
            }
            if (!swimming) {
                if (lives > 0) {
                    takeFroggyLife();
                } else if (lives < 1) {
                    setGameOver();
                }
            }
        }
    }

    @Override
    public void checkIfWin() {
        Rectangle winRec = new Rectangle(0, 0, 600, 40);
        Rectangle froggyRec = froggy.getBounds();
        if (froggyRec.intersects(winRec)) {
            Object[] options = {"Menu", "Quit!"};
            int choice = JOptionPane.showOptionDialog(getParent(), "YOU WIN!!!",
                    "YOU WIN", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options,
                    options[0]);
            if (choice == 0) {
                terminate();
                Game.setIsLevelTwo(false);
                Game.setIsMenu(true);
                Game.panelSettings();
            } else if (choice == 1) {
                terminate();
                Game.setIsLevelTwo(false);
                System.exit(0);
            }
            terminate();
            froggy = new Froggy(220, 435, 1);
        }
    }

    @Override
    public void checkSpriteIntersects() {
        for (int i = 0; i < items.size(); i++) {
            for (int k = i + 1; k < items.size(); k++) {
                Rectangle sprite1 = items.get(i).getBounds();
                Rectangle sprite2 = items.get(k).getBounds();
                if (sprite1.intersects(sprite2)) {
                    System.out.println();
                    Random ran = new Random();
                    int random = ran.nextInt(125) + 50;
                    items.get(k).setX(items.get(k).getX() - random);
                }
            }
        }
    }

    @Override
    public void run() {
        while (running) {
            updateFrog();
            updateItems();
            checkSpriteIntersects();
            checkPosition();
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

    private void takeFroggyLife() {
        froggy.setX(250);
        froggy.setY(435);
        lives--;
    }

    private void setGameOver() {
        Game.setIsLastLevelOne(false);
        terminate();
        Game.setIsLevelTwo(false);
        Game.setIsGameOver(true);
        Game.panelSettings();
        froggy = new Froggy(220, 435, 1);
    }

    public static int getDelay() {
        return delay;
    }

    public static void setDelay(int delay) {
        LevelTwo.delay = delay;
    }

    public static void setLives(int lives) {
        LevelTwo.lives = lives;
    }

    public static void setRunning(boolean running) {
        LevelTwo.running = running;
    }

    public void terminate() {
        running = false;
    }

    public void checkCollisions() {
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
