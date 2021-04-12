package gui;

import benchmark.Storage;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

public class AddingPanel extends JPanel {
    private final Integer[] sizes = {10000, 30000, 50000, 70000, 100000, 120000, 150000, 300000, 500000, 1000000, 2000000};
    private final String[] types = {"Integer", "Double", "Long", "String"};
    private final JComboBox cbSize = new JComboBox(sizes);
    private final JComboBox cbType = new JComboBox(types);
    private int size = 10000;
    private String type = "Integer";
    private List<Integer> results;
    private Storage storage = Storage.getInstance();


    public AddingPanel() {
        setBackground(Color.darkGray);
        setLayout(new BorderLayout());
        JLabel name = new JLabel("Adding elements");
        JButton button = new JButton("START");
        cbSize.setSelectedIndex(0);
        cbType.setSelectedIndex(0);
        cbSize.setFont(new Font("Courier", Font.PLAIN, 15));
        cbType.setFont(new Font("Courier", Font.PLAIN, 15));

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1, 20, 20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(60,300,0,300));
        centerPanel.setBackground(Color.darkGray);

        panel1.setBackground(Color.darkGray);
        panel2.setBackground(Color.darkGray);
        panel3.setBackground(Color.darkGray);

        centerPanel.add(panel1);
        centerPanel.add(panel2);
        centerPanel.add(panel3);

        panel1.setBorder(BorderFactory.createTitledBorder(
                null, "Size", TitledBorder.CENTER, TitledBorder.TOP, new Font("times new roman", Font.PLAIN, 20), Color.WHITE));
        panel2.setBorder(BorderFactory.createTitledBorder(
                null, "Type", TitledBorder.CENTER, TitledBorder.TOP, new Font("times new roman", Font.PLAIN, 20), Color.WHITE));

        panel1.add(cbSize);
        panel2.add(cbType);
        panel3.add(button);

        add(name, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        cbSize.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                size = (int) cbSize.getSelectedItem();
            }
        });

        cbType.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = (String) cbType.getSelectedItem();
            }
        });

        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillCollections();
                removeAll();
                name.setText(size + " " +  type + "s added" );
                add(name, BorderLayout.NORTH);
                add(new Chart(results, size, type), BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        name.setFont(new Font("Courier", Font.BOLD, 25));
        name.setForeground(new Color(52, 152, 219));

        button.setBackground(new Color(25, 111, 61));
        button.setForeground(Color.white);
        button.setFocusPainted(false);
        button.setFont(new Font("Courier", Font.BOLD, 22));
    }

    private void fillCollections() {
        results = new ArrayList<>();
        results.add(storage.addToArrayList(size, type));
        results.add(storage.addToHashSet(size, type));
        results.add(storage.addToHashMap(size, type));

    }
}
