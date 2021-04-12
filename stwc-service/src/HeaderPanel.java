import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HeaderPanel extends JPanel {
    private final JButton btnAdd;
    private final Action actionAdd;
    private Frame frame;

    public HeaderPanel(Frame frame) {
        this.frame = frame;
        actionAdd = new AbstractAction("Add new stopwatch") {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.addNew();
            }
        };

        btnAdd = new JButton(actionAdd);
        btnAdd.setBackground(Color.DARK_GRAY);
        btnAdd.setForeground(Color.white);
        btnAdd.setFocusPainted(false);
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 25));

        add(btnAdd);

    }
}
