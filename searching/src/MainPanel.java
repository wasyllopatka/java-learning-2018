import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainPanel extends JPanel{
    private static JPanel centerPanel;

    public MainPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(130, 128, 128));

        // Header panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        ImageIcon homeIcon = new ImageIcon("header.png");
        JButton btnHome = new JButton(homeIcon);
        headerPanel.add(btnHome);

        // Center panel
        centerPanel = new JPanel();
        centerPanel.setBackground(new Color(130, 128, 128));

        centerPanel.add(new SearchPanel());

        add(headerPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        btnHome.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.removeAll();
                centerPanel.add(new SearchPanel());
                centerPanel.revalidate();
                centerPanel.repaint();
            }
        });

        headerPanel.setPreferredSize(new Dimension(1100, 200));
        headerPanel.setBackground(new Color(130, 128, 128));

        btnHome.setFocusPainted(false);
        btnHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Border emptyBorder = BorderFactory.createEmptyBorder();
        btnHome.setBorder(emptyBorder);
        btnHome.setContentAreaFilled(false);
        btnHome.setBorderPainted(false);

    }

    public static void addWorkPanel() {
        centerPanel.removeAll();
        centerPanel.add(new ViewPanel());
        centerPanel.revalidate();
        centerPanel.repaint();
    }

}