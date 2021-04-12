package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Panel extends JPanel {
    private AddingPanel addingPanel;
    private RemovingPanel removingPanel;
    private SearchingPanel searchingPanel;

    public  Panel() {
        setLayout(new BorderLayout());
        setBackground(Color.darkGray);

        //Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        headerPanel.setBackground(new Color(131, 145, 146));
        ImageIcon homeIcon = new ImageIcon("benchmark.png");
        JButton btnHome = new JButton(homeIcon);
        JLabel nameHeader = new JLabel("Collections benchmark for ArrayList, HashSet and HashMap");
        headerPanel.add(btnHome, BorderLayout.WEST);
        headerPanel.add(nameHeader, BorderLayout.CENTER);
        addingPanel = new AddingPanel();

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4, 1, 20, 20));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(60,200,0,200));
        buttonsPanel.setBackground(Color.darkGray);
        JButton btnAdd = new JButton(" Add elements");
        JButton btnRemove = new JButton(" Remove elements");
        JButton btnSearch = new JButton(" Search elements");
        buttonsPanel.add(btnAdd);
        buttonsPanel.add(btnRemove);
        buttonsPanel.add(btnSearch);

        add(headerPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.CENTER);

        btnHome.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                add(new Panel());
                revalidate();
                repaint();
            }
        });

        btnAdd.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               buttonsPanel.setVisible(false);
                add(addingPanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        btnRemove.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removingPanel = new RemovingPanel();
                buttonsPanel.setVisible(false);
                add(removingPanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        btnSearch.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchingPanel = new SearchingPanel();
                buttonsPanel.setVisible(false);
                add(searchingPanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });


        btnHome.setFocusPainted(false);
        btnHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnHome.setContentAreaFilled(false);
        btnHome.setBorderPainted(false);

        btnAdd.setBackground(new Color(52, 152, 219));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFocusPainted(false);
        btnAdd.setFont(new Font("Courier", Font.BOLD, 30));

        btnRemove.setBackground(new Color(52, 152, 219));
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFocusPainted(false);
        btnRemove.setFont(new Font("Courier", Font.BOLD, 30));

        btnSearch.setBackground(new Color(52, 152, 219));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFocusPainted(false);
        btnSearch.setFont(new Font("Courier", Font.BOLD, 30));

        nameHeader.setFont(new Font("Courier", Font.BOLD, 25));
        nameHeader.setForeground(Color.white);
    }
}
