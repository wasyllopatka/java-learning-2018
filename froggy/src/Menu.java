import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Menu extends JPanel {

    private static boolean easy;
    private static boolean normal;
    private static boolean hard;

    public Menu() {
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        JLabel label = new JLabel("FROGGER", SwingConstants.CENTER);
        headerPanel.add(label, BorderLayout.CENTER);
        headerPanel.setBackground(new Color(42, 195, 58));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1, 0, 0));
        JButton easyBtn = new JButton("EASY");
        JButton normalBtn = new JButton("NORMAL");
        JButton hardBtn = new JButton("HARD");

        centerPanel.add(easyBtn);
        centerPanel.add(normalBtn);
        centerPanel.add(hardBtn);

        easyBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEasy(true);
                LevelOne.setDelay(12);
                Game.setNewLifeL1();
                Game.setIsMenu(false);
                Game.setIsLevelOne(true);
                Game.panelSettings();
            }
        });

        normalBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setNormal(true);
                Game.setNewLifeL1();
                LevelOne.setDelay(7);
                Game.setIsMenu(false);
                Game.setIsLevelOne(true);
                Game.panelSettings();
            }
        });

        hardBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setHard(true);
                Game.setNewLifeL1();
                LevelOne.setDelay(3);
                Game.setIsMenu(false);
                Game.setIsLevelOne(true);
                Game.panelSettings();
            }
        });

        label.setFont(new Font("Courier", Font.BOLD, 50));

        easyBtn.setBackground(Color.darkGray);
        easyBtn.setForeground(Color.white);
        easyBtn.setFocusPainted(false);
        easyBtn.setFont(new Font("Courier", Font.BOLD, 15));

        normalBtn.setBackground(Color.darkGray);
        normalBtn.setForeground(Color.white);
        normalBtn.setFocusPainted(false);
        normalBtn.setFont(new Font("Courier", Font.BOLD, 15));

        hardBtn.setBackground(Color.darkGray);
        hardBtn.setForeground(Color.white);
        hardBtn.setFocusPainted(false);
        hardBtn.setFont(new Font("Courier", Font.BOLD, 15));

        add(headerPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    public static boolean isEasy() {
        return easy;
    }

    public static void setEasy(boolean easy) {
        Menu.easy = easy;
    }

    public static boolean isNormal() {
        return normal;
    }

    public static void setNormal(boolean normal) {
        Menu.normal = normal;
    }

    public static boolean isHard() {
        return hard;
    }

    public static void setHard(boolean hard) {
        Menu.hard = hard;
    }
}
