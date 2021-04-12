package gui;

import benchmark.Storage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class SearchingPanel extends JPanel {
    private List<Integer> results;
    private Storage storage = Storage.getInstance();
    private AddingPanel addingPanel;
    private JLabel label = new JLabel();

    public SearchingPanel() {
        setBackground(Color.DARK_GRAY);
        setLayout(new GridLayout(4, 1, 20, 20));

        JButton searchRandomButton = new JButton("Search random");
        JButton searchThreeButton = new JButton("Search three elements");

        searchRandomButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchRandom();
                removeAll();
                setLayout(new BorderLayout());
                label.setText("Random elements found :" +
                        " in ArrayList, id - " + storage.getRandomArrayListObject() +
                        " in HashSet, id - " + storage.getRandomHashSetObject() +
                        " in HashMap, id - " + storage.getRandomHashMapObject());
                add(label, BorderLayout.NORTH);
                add(new Chart(results, storage.getLastSize(), storage.getLastType()), BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        searchThreeButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchThree();
                removeAll();
                setLayout(new BorderLayout());
                label.setText("Three elements found :" +
                        " in ArrayList, id - " + storage.getSearchALEntity().toString() +
                        " in HashSet, id - " + storage.getSearchHSEntity().toString() +
                        " in HashMap, id - " + storage.getSearchHMEntity().toString());
                add(label, BorderLayout.NORTH);
                add(new Chart(results, storage.getLastSize(), storage.getLastType()), BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        JButton addButton = new JButton("Add elements");
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addingPanel = new AddingPanel();
                removeAll();
                setLayout(new BorderLayout());
                add(addingPanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        JLabel labelEmpty = new JLabel("Collections are empty!");
        if (storage.getList().isEmpty() || storage.getSet().isEmpty() || storage.getMap().isEmpty()) {
            add(labelEmpty);
            add(addButton);
        } else {
            add(searchRandomButton);
            add(searchThreeButton);
        }


        // Elements styling
        label.setFont(new Font("Courier", Font.BOLD, 15));
        label.setForeground(new Color(52, 152, 219));

        labelEmpty.setFont(new Font("Courier", Font.BOLD, 25));
        labelEmpty.setForeground(new Color(52, 152, 219));

        addButton.setBackground(new Color(13, 197, 76));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setFont(new Font("Courier", Font.BOLD, 20));

        searchRandomButton.setBackground(new Color(52, 152, 219));
        searchRandomButton.setForeground(Color.WHITE);
        searchRandomButton.setFocusPainted(false);
        searchRandomButton.setFont(new Font("Courier", Font.BOLD, 30));

        searchThreeButton.setBackground(new Color(52, 152, 219));
        searchThreeButton.setForeground(Color.WHITE);
        searchThreeButton.setFocusPainted(false);
        searchThreeButton.setFont(new Font("Courier", Font.BOLD, 30));
    }

    public void searchRandom() {
        results = new ArrayList<>();
        results.add(storage.searchRandomInArrayList());
        results.add(storage.searchRandomInHashSet());
        results.add(storage.searchRandomInHashMap());
    }

    public void searchThree() {
        results = new ArrayList<>();
        results.add(storage.searchThreeInArrayList());
        results.add(storage.searchThreeInHashSet());
        results.add(storage.searchThreeInHashMap());
    }
}
