package swing.view;

import javax.swing.*;

public class SwingUtils {
    public void disposePanel(JPanel panel) {
        JFrame opened = (JFrame) panel.getTopLevelAncestor();
        opened.dispose();
    }
}
