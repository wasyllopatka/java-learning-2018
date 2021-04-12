import java.awt.event.KeyEvent;

public class Froggy extends Sprite {
    private int stepX;
    private int stepY;

    public Froggy(int x, int y, int direction) {
        super(x, y, direction);
        init();
    }

    private void init() {
        loadImage("img/froggy.png");
        getImageDimensions();
    }

    public void move() {
        x += stepX;
        y += stepY;

        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
    }

    private int getStep() {
        int step = 0;
        if (Menu.isEasy() && (Game.isIsLevelOne())) {
            step = 5;
        } else if (Menu.isNormal() && (Game.isIsLevelOne())) {
            step = 3;
        } else if (Menu.isHard() && (Game.isIsLevelOne())) {
            step = 2;
        } else if (Menu.isEasy() && (Game.isIsLevelTwo())) {
            step = 7;
        } else if (Menu.isNormal() && (Game.isIsLevelTwo())) {
            step = 5;
        } else if (Menu.isHard() && (Game.isIsLevelTwo())) {
            step = 2;
        }
        return step;
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            stepX = -1 * getStep();
        }

        if (key == KeyEvent.VK_RIGHT) {
            stepX = getStep();
        }

        if (key == KeyEvent.VK_UP) {
            stepY = -1 * getStep();
        }

        if (key == KeyEvent.VK_DOWN) {
            stepY = getStep();
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            stepX = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            stepX = 0;
        }

        if (key == KeyEvent.VK_UP) {
            stepY = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            stepY = 0;
        }
    }
}