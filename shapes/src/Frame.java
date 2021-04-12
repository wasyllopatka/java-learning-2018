import javax.swing.*;

public class Frame extends JFrame {

    public Frame() {
        add(new PaintPanel());
        setTitle("Shapes");
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setSize(1100, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
