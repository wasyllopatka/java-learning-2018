package swing.view;

import model.Genre;
import storage.AbstractStorage;
import storage.StorageFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddGenre extends JPanel {
    private AbstractStorage genreStorage;
    private SwingUtils utils = new SwingUtils();
    private JTextField genreNameTF = new JTextField(12);

    public AddGenre() {
        StorageFactory storageFactory = new StorageFactory();
        List<AbstractStorage> storages = storageFactory.getStorage(Panel.getSource());
        genreStorage = storages.get(1);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        addComponents(new JLabel("Genre name"), genreNameTF, this, gbc);
        JButton createBtn = new JButton("Create");
        add(createBtn);

        createBtn.addActionListener(e -> {
            genreStorage.add(setGenre());
            JOptionPane.showMessageDialog(this, "Genre created successfully!");
            utils.disposePanel(this);
            new Frame();
        });
    }

    private void addComponents(JLabel label, JTextField tf, JPanel p, GridBagConstraints gbc) {
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        p.add(label, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        p.add(tf, gbc);
    }

    private Genre setGenre() {
        Genre genre = new Genre();
        genre.setName(genreNameTF.getText());
        return genre;
    }
}
