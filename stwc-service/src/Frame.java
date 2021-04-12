import javax.swing.*;

public class Frame extends javax.swing.JFrame{
    final private int numPanels = 11;
    private int numWatches = 3;

    public Frame() {
        setTitle("Stopwatches");
        setLayout(new java.awt.GridLayout(numPanels, 1));
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        add(new HeaderPanel(this));

        for (int i =0; i<3; i++)
            add(new Panel());

        pack();
        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        repaint();
    }

    public void addNew() {
        if (numWatches < 10) {
            add(new Panel());
            numWatches++;
            pack();
            setSize(800, 800);
            revalidate();
            repaint();
        } else {
            JOptionPane.showMessageDialog(this,"Maximum numbers of stopwatches is reached!");
            return;
        }
    }
}