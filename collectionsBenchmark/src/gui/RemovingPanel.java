package gui;

import benchmark.Storage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class RemovingPanel extends JPanel {

    private AddingPanel addingPanel;
    private List<Integer> results;
    private Storage storage = Storage.getInstance();


    public RemovingPanel() {
        setBackground(Color.darkGray);
        setLayout(new GridLayout(6, 1, 100, 20));

        String[] columns = new String[] {"Name", "Size", "Type"};
        JLabel label = new JLabel("Current state of collections");
        JLabel labelEmpty = new JLabel("Collections are empty!");
        JButton addButton = new JButton("Add elements");
        JButton button = new JButton("Start removing");
        Object[][] data = new Object[][] {
                {"ArrayList", Storage.getInstance().getList().size(), Storage.getInstance().getLastType()},
                {"HashSet", Storage.getInstance().getSet().size(), Storage.getInstance().getLastType()},
                {"HashMap", Storage.getInstance().getMap().size(), Storage.getInstance().getLastType()},
        };
        JTable table = new JTable(data, columns);

        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removing();
                removeAll();
                setLayout(new BorderLayout());
                label.setText(storage.getLastSize() + " " +  storage.getLastType() + "s removed!" );
                add(label, BorderLayout.NORTH);
                add(new Chart(results, storage.getLastSize(), storage.getLastType()), BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addingPanel = new AddingPanel();
                removeAll();
                setLayout(new BorderLayout());
                add(addingPanel, BorderLayout.WEST);
                revalidate();
                repaint();
            }
        });

        if (storage.getList().isEmpty() || storage.getSet().isEmpty() || storage.getMap().isEmpty()) {
            add(labelEmpty);
            add(addButton);
        } else {
            add(label);
            add(new JScrollPane(table));
            add(button);
        }

        labelEmpty.setFont(new Font("Courier", Font.BOLD, 25));
        labelEmpty.setForeground(new Color(52, 152, 219));

        label.setFont(new Font("Courier", Font.BOLD, 25));
        label.setForeground(new Color(52, 152, 219));

        button.setBackground(new Color(228, 49, 49));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Courier", Font.BOLD, 20));

        addButton.setBackground(new Color(13, 197, 76));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setFont(new Font("Courier", Font.BOLD, 20));

    }

    private void removing() {
        results = new ArrayList<>();
        results.add(Storage.getInstance().removeFromArrayList());
        results.add(Storage.getInstance().removeFromHashSet());
        results.add(Storage.getInstance().removeFromHashMap());
    }
}
