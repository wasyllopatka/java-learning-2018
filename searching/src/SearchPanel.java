import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchPanel extends JPanel {

    public static String[] searchValues;
    private String inputText = "Input the text here";
    private JFileChooser chooser;
    private List<String> words = new ArrayList<>();

    public SearchPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(130, 128, 128));

        JTextField input = new JTextField(30) {
            public void addNotify() {
                super.addNotify();
                requestFocus();
            }
        };

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(130, 128, 128));

        JButton btnSearch = new JButton("Get text");
        JButton btnOpen = new JButton("Open file");

        btnPanel.add(btnSearch);
        btnPanel.add(btnOpen);

        add(input, BorderLayout.NORTH);
        add(btnPanel, BorderLayout.CENTER);

        // Listeners :

        input.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                input.setText("");
                input.setForeground(Color.WHITE);
            }

            @Override
            public void focusLost(FocusEvent e) {}});

        btnSearch.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchValues = input.getText().split("[\\W_]+");
                if (input.getText().equals("") || input.getText().equals(inputText)) {
                    input.setText(inputText);
                    input.setForeground(Color.LIGHT_GRAY);
                } else {
                    ViewPanel.words = Arrays.asList(searchValues);
                    MainPanel.addWorkPanel();
                }
            }
        });

        btnOpen.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chooser == null) {
                    chooser = new JFileChooser();
                    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    chooser.setAcceptAllFileFilterUsed(false);
                    chooser.addChoosableFileFilter(new FileFilter() {
                        @Override
                        public boolean accept(File f) {
                            return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
                        }

                        @Override
                        public String getDescription() {
                            return "Text Files (*.txt)";
                        }
                    });
                }

                switch (chooser.showOpenDialog(SearchPanel.this)) {
                    case JFileChooser.APPROVE_OPTION:
                        try (BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
                            String line;
                            while ((line = br.readLine()) != null) {
                                words.addAll(Arrays.asList(line.split("[\\W_]+")));
                                ViewPanel.words = words;
                            }
                        } catch (IOException exp) {
                            exp.printStackTrace();
                            JOptionPane.showMessageDialog(SearchPanel.this, "Failed to read file", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        MainPanel.addWorkPanel();
                        break;
                }
            }
        });


        // Elements styling

        input.setBackground(new Color(130, 128, 128));
        input.setForeground(Color.WHITE);
        input.setCaretColor(Color.WHITE);
        input.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
        Font fieldFont = new Font("Arial", Font.PLAIN, 20);
        input.setFont(fieldFont);

        btnSearch.setBackground(new Color(130, 128, 128));
        btnSearch.setForeground(Color.orange);
        btnSearch.setFocusPainted(false);
        btnSearch.setFont(new Font("Courier", Font.BOLD, 12));
        btnSearch.setMargin(new Insets(15, 20, 10, 20));

        btnOpen.setBackground(new Color(130, 128, 128));
        btnOpen.setForeground(Color.white);
        btnOpen.setFocusPainted(false);
        btnOpen.setFont(new Font("Courier", Font.BOLD, 12));
        btnOpen.setMargin(new Insets(15, 20, 10, 20));

    }
}
