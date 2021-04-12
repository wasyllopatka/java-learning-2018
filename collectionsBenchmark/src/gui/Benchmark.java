package gui;

import javax.swing.*;
import java.awt.*;

public class Benchmark extends JFrame{

    public Benchmark() {
        add(new Panel());
        setTitle("Collection benchmark");
        setSize(1100, 800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.BLACK);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Benchmark();

    }
}
