import javax.swing.*;

public class Game {

    private static JFrame frame = new JFrame("Frogger");
    private static Menu menu = new Menu();
    private static LevelOne levelOne = new LevelOne();
    private static LevelTwo levelTwo = new LevelTwo();
    private static GameOver gameOver = new GameOver();

    private static boolean isMenu = true,
            isLevelOne = false,
            isLevelTwo = false,
            isGameOver = false,
            isLastLevelOne = true;

    public static void main(String[] args) {
        frame.add(menu);
        frame.setTitle("Frogger");
        frame.setSize(600, 530);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void panelSettings() {
        if (isMenu) {
            frame.getContentPane().removeAll();
            frame.add(getMenu());
            frame.revalidate();
            frame.repaint();
        } else if (isLevelOne) {
            frame.getContentPane().removeAll();
            levelOne.setRunning(true);
            levelOne.setVisible(true);
            frame.add(levelOne);
            levelOne.requestFocus();
            frame.revalidate();
            frame.repaint();
        } else if (isLevelTwo) {
            frame.getContentPane().removeAll();
            frame.add(levelTwo);
            levelTwo.requestFocus();
            levelTwo.setVisible(true);
            frame.revalidate();
            frame.repaint();
        } else if (isGameOver) {
            frame.getContentPane().removeAll();
            frame.add(gameOver);
            gameOver.requestFocus();
            frame.revalidate();
            frame.repaint();
        }
    }

    public static void setIsMenu(boolean menu) {
        Game.isMenu = menu;
    }

    public static void setIsLevelOne(boolean levelOne) {
        Game.isLevelOne = levelOne;
    }

    public static void setIsLevelTwo(boolean levelTwo) {
        Game.isLevelTwo = levelTwo;
    }

    public static void setIsGameOver(boolean gameOver) {
        Game.isGameOver = gameOver;
    }

    public static void setIsLastLevelOne(boolean lastOne) {
        Game.isLastLevelOne = lastOne;
    }

    public static boolean isIsLastLevelOne() {
        return isLastLevelOne;
    }

    public static boolean isIsLevelOne() {
        return isLevelOne;
    }

    public static boolean isIsLevelTwo() {
        return isLevelTwo;
    }

    public static void setNewLifeL1() {
        levelOne.setLives(3);
    }

    public static void setNewLifeL2() {
        levelTwo.setLives(3);
    }

    public static Menu getMenu() {
        return menu;
    }


}
