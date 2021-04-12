import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Panel extends JPanel {
    private final JTextField field;
    private final JLabel nameDelay = new JLabel("Delay");
    private final JLabel  namePeriod= new JLabel("Period");
    private final String [] numbers= {"1","2","5","10"};
    private final JComboBox cbDelay = new JComboBox(numbers);
    private final JComboBox cbPeriod = new JComboBox(numbers);
    private final JButton btnStart, btnPause, btnReset;
    private final Action actionStart, actionPause, actionReset;
    private long delay = 1;
    private long period = 1;
    private Stopwatch stopwatch;

    public Panel() {
        /*
            Actions methods:
         */

        actionStart = new AbstractAction("Start") {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionStart.setEnabled(false);
                actionPause.setEnabled(true);
                actionReset.setEnabled(false);
                btnPause.requestFocusInWindow();
                stopwatch.start();
            }
        };

        actionPause = new AbstractAction("Pause") {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionStart.setEnabled(true);
                actionPause.setEnabled(false);
                actionReset.setEnabled(true);
                btnStart.requestFocusInWindow();
                stopwatch.pause();
            }
        };

        actionReset = new AbstractAction("Reset") {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionStart.setEnabled(true);
                actionPause.setEnabled(false);
                actionReset.setEnabled(false);
                btnStart.requestFocusInWindow();
                stopwatch.reset();
                field.setText(getResetTime());
            }
        };

        cbDelay.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delay = Long.valueOf((String) cbDelay.getSelectedItem());
            }
        });

        cbPeriod.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                period = Long.valueOf((String) cbPeriod.getSelectedItem());
            }
        });

        /*
            Gui elements and styling
         */
        stopwatch = new Stopwatch( this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        setBackground(Color.lightGray);
        setBorder(BorderFactory.createLineBorder(Color.black));
        cbDelay.setSelectedIndex(0);
        nameDelay.setFont(new Font("Courier", Font.BOLD, 15));
        add(nameDelay);
        cbDelay.setFont(new Font("Courier", Font.PLAIN, 15));
        add(cbDelay);
        namePeriod.setFont(new Font("Courier", Font.BOLD, 15));
        add(namePeriod);
        cbPeriod.setFont(new Font("Courier", Font.PLAIN, 15));
        add(cbPeriod);
        field = new JTextField(getResetTime());
        field.setEditable(false);
        field.setFont(new Font("Courier", Font.BOLD, 40));
        add(field);
        add(buttonPanel);

        // buttons:
        btnStart = new JButton(actionStart);
        btnStart.setBackground(new Color(59, 89, 182));
        btnStart.setForeground(Color.white);
        btnStart.setFocusPainted(false);
        btnStart.setFont(new Font("Courier", Font.BOLD, 25));
        buttonPanel.add(btnStart);
        btnPause = new JButton(actionPause);
        btnPause.setBackground(Color.orange);
        btnPause.setForeground(Color.blue);
        btnPause.setFocusPainted(false);
        btnPause.setFont(new Font("Courier", Font.BOLD, 25));
        buttonPanel.add(btnPause);
        btnReset = new JButton(actionReset);
        btnReset.setBackground(Color.red);
        btnReset.setForeground(Color.white);
        btnReset.setFocusPainted(false);
        btnReset.setFont(new Font("Courier", Font.BOLD, 25));
        buttonPanel.add(btnReset);
    }

    private final String buildTime(long elapsed) {
        long seconds = elapsed / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        return String.format("%02d:%02d:%02d", hours, minutes % 60, seconds % 60);
    }

    public void displayTime(long timeValue) {
        this.field.setText(buildTime(timeValue));
    }

    public String getResetTime() {
        return buildTime(0);
    }

    public long getDelay() {
        return delay;
    }

    public long getPeriod() {
        return period;
    }
}
