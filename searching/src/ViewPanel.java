import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewPanel extends JPanel {

    public static List<String> words;
    public static List<String> palindromes;
    private JTextArea textArea;
    private StringBuilder builder;
    private JLabel labelResult;
    private JLabel labelFound;
    private PalindromeService palindromeService;
    private DefaultListModel<String> listModel;

    public ViewPanel() {
        setLayout(new BorderLayout());

        builder = new StringBuilder();
        palindromes = new ArrayList<>();
        listModel = new DefaultListModel<>();
        palindromeService = new PalindromeService();
        labelFound = new JLabel();
        textArea = new JTextArea(12, 35);
        labelResult = new JLabel("");

        JPanel contentPanel = new JPanel(new BorderLayout());
        JPanel actionPanel = new JPanel();
        JPanel resultPanel = new JPanel(new BorderLayout());

        JButton btnPal = new JButton("Get palindromes");

        JList<String> listPal = new JList<>(listModel);

        setTextArea();
        setWords();

        // Adding elements to panels :
        contentPanel.add(labelFound, BorderLayout.NORTH);
        contentPanel.add(textArea, BorderLayout.CENTER);
        actionPanel.add(btnPal);
        resultPanel.add(labelResult, BorderLayout.NORTH);
        resultPanel.add(listPal, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.WEST);
        add(actionPanel, BorderLayout.NORTH);

        // Listeners:

        btnPal.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setWords();
                palindromes = palindromeService.getPalindrome(words);
                if (palindromes.size() != 0 && words.size() != 0) {
                    setListModel();
                    setResultExist();
                    add(resultPanel, BorderLayout.EAST);
                    setFoundWordsExist();
                    revalidate();
                    repaint();
                } else {
                    setResultNotExist();
                    add(resultPanel, BorderLayout.EAST);
                    revalidate();
                    repaint();
                }
            }
        });


        // Elements styling

        setBackground(new Color(130, 128, 128));

        contentPanel.setBackground(new Color(130, 128, 128));

        actionPanel.setBackground(new Color(130, 128, 128));
        actionPanel.setLayout(new GridLayout(1, 8, 0, 0));

        resultPanel.setBackground(new Color(130, 128, 128));
        resultPanel.setBorder(new EmptyBorder(10, 15, 10, 10));

        listPal.setBackground(new Color(130, 128, 127));
        listPal.setFont(new Font("Arial", Font.PLAIN, 16));
        listPal.setForeground(Color.blue);

        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setLineWrap(true);
        textArea.setMargin(new Insets(15, 15, 15, 15));
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));

        labelResult.setFont(new Font("Arial", Font.PLAIN, 16));
        labelResult.setForeground(Color.WHITE);

        labelFound.setForeground(Color.WHITE);

        btnPal.setBackground(new Color(130, 128, 128));
        btnPal.setForeground(Color.blue);
        btnPal.setFocusPainted(false);
        btnPal.setFont(new Font("Courier", Font.BOLD, 12));
        btnPal.setMargin(new Insets(15, 20, 10, 20));
    }

    // Additional methods:

    private void setTextArea() {
        for (String word : words) {
            builder.append(word).append(" ");
            textArea.setText(builder.toString());
        }
    }

    private void setFoundWordsExist() {
        labelFound.setText("<html>Found " + words.size() + " words </html>");
    }

    private void setFoundWordsNotExist() {
        labelFound.setText("<html>No words found</html>");
    }

    private void setResultExist() {
        labelResult.setText("<html> " + palindromes.size() + " palindrome(s): " + "</html>");
    }

    private void setResultNotExist() {
        listModel.clear();
        labelResult.setText("<html>No palindromes!" + "</html>");
    }

    private void setListModel() {
        listModel.clear();
        for (String pal : palindromes) {
            listModel.addElement(pal);
        }
    }

    private void setWords() {
        if (!textArea.getText().equals("")) {
            String[] array = textArea.getText().split("[\\W_]+");
            if (array.length != 0) {
                words = Arrays.asList(array);
                setFoundWordsExist();
            }
        } else {
            setFoundWordsNotExist();
            revalidate();
            repaint();
        }
    }
}
