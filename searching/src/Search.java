import javax.swing.*;
import java.awt.*;

public class Search extends JFrame {
    public Search() {
        add(new MainPanel());
        setTitle("Search in text");
        setSize(1100, 800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.BLACK);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Search();
    }

}
