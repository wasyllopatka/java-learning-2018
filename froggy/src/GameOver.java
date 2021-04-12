import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameOver extends JPanel {

    public GameOver() {
        setLayout(new GridLayout(4, 1, 0, 0));
        setBackground(Color.black);

        JButton btnTry = new JButton("Try again!");
        btnTry.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.setIsGameOver(false);
                if (Game.isIsLastLevelOne()) {
                    Game.setIsLevelOne(true);
                    Game.setNewLifeL1();
                    Game.panelSettings();
                } else {
                    Game.setIsLevelTwo(true);
                    Game.setNewLifeL2();
                    LevelTwo.setRunning(true);
                    LevelTwo.setDelay(LevelTwo.getDelay());
                    Game.panelSettings();
                }
            }
        });

        JButton btnMenu = new JButton("Back to MENU");
        btnMenu.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.setIsGameOver(false);
                Game.setIsMenu(true);
                Game.setIsLastLevelOne(true);
                Game.setNewLifeL1();
                Game.panelSettings();
            }
        });

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JLabel label = new JLabel("GAME OVER", SwingConstants.CENTER);
        label.setFont(new Font("Courier", Font.BOLD, 50));
        label.setForeground(Color.WHITE);

        btnTry.setBackground(Color.darkGray);
        btnTry.setForeground(Color.white);
        btnTry.setFocusPainted(false);
        btnTry.setFont(new Font("Courier", Font.BOLD, 20));

        btnMenu.setBackground(Color.darkGray);
        btnMenu.setForeground(Color.white);
        btnMenu.setFocusPainted(false);
        btnMenu.setFont(new Font("Courier", Font.BOLD, 20));

        btnExit.setBackground(Color.darkGray);
        btnExit.setForeground(Color.white);
        btnExit.setFocusPainted(false);
        btnExit.setFont(new Font("Courier", Font.BOLD, 20));

        add(label);
        add(btnTry);
        add(btnMenu);
        add(btnExit);
    }
}
