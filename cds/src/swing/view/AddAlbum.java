package swing.view;

import model.Album;
import model.Genre;
import storage.AbstractStorage;
import storage.StorageFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class AddAlbum extends JPanel {
    private AbstractStorage albumStorage;
    private AbstractStorage genreStorage;
    private SwingUtils utils = new SwingUtils();
    private final JTextField albumNameTF = new JTextField(12);
    private final JTextField artistTF = new JTextField(12);
    private final JTextField yearsTF = new JTextField(12);
    private int selectedId = 0;

    public AddAlbum() {
        StorageFactory storageFactory = new StorageFactory();
        List<AbstractStorage> storages = storageFactory.getStorage(Panel.getSource());
        albumStorage = storages.get(0);
        genreStorage = storages.get(1);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 2, 2, 2);
        JComboBox<Genre> genreCB = new JComboBox<>();
        List<Genre> genresList = new ArrayList<>();
        genresList = genreStorage.findAll();
        for (Genre g : genresList) {
            genreCB.addItem(g);
        }

        addComponents(new JLabel("Album name"), albumNameTF, this, gbc);
        addComponents(new JLabel("Artist"), artistTF, this, gbc);
        addComponents(new JLabel("Year"), yearsTF, this, gbc);
        addComboBox(new JLabel("Genre"), genreCB, this, gbc);
        JButton createBtn = new JButton("Create");
        add(createBtn);

        createBtn.addActionListener(e -> {
            albumStorage.add(setAlbum());
            JOptionPane.showMessageDialog(this, "Album created successfully!");
            utils.disposePanel(this);
            new Frame();
        });

        genreCB.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Genre genre = (Genre) genreCB.getSelectedItem();
                selectedId = genre.getId();
            }
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

    private void addComboBox(JLabel label, JComboBox cb, JPanel p, GridBagConstraints gbc) {
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        p.add(label, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        p.add(cb, gbc);
    }

    private Album setAlbum() {
        Album album = new Album();
        album.setGenreId(selectedId);
        album.setName(albumNameTF.getText());
        album.setArtist(artistTF.getText());
        album.setYear(Integer.parseInt(yearsTF.getText()));
        album.setGenreId(selectedId);
        return album;
    }
}
