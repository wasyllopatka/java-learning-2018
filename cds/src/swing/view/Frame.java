package swing.view;

import javax.swing.*;

public class Frame extends JFrame {

    public Frame() {
        add(new Panel(Panel.getSource()));
        setTitle("CD collection using XML/JSON/POSTGRES");
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
