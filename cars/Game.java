import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    public Game() {
        add(new RaceArena());
        setTitle("Race");
        setSize(700, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.BLACK);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Game();
    }
}
