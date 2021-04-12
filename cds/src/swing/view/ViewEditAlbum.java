package swing.view;

import model.Album;
import model.Genre;
import storage.AbstractStorage;
import storage.StorageFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ViewEditAlbum extends JPanel {
    private AbstractStorage albumStorage;
    private AbstractStorage genreStorage;
    private int selectedId = 0;
    private SwingUtils utils = new SwingUtils();
    private JTextField nameTF = new JTextField(12);
    private JTextField artistTF = new JTextField(12);
    private JTextField yearTF = new JTextField(12);
    private JComboBox<Genre> genreCB = new JComboBox<>();
    private JButton btnUpdate = new JButton("Update");
    private JLabel nameLabel = new JLabel("ok");
    private JLabel artistLabel = new JLabel("ok");
    private JLabel yearLabel = new JLabel("ok");
    private JLabel genreLabel = new JLabel("ok");


    public ViewEditAlbum(int albumId) {
        StorageFactory storageFactory = new StorageFactory();
        List<AbstractStorage> storages = storageFactory.getStorage(Panel.getSource());
        albumStorage = storages.get(0);
        genreStorage = storages.get(1);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);

        addView(new JLabel("Album name"), nameLabel, this, gbc);
        addView(new JLabel("Artist"), artistLabel, this, gbc);
        addView(new JLabel("Year"), yearLabel, this, gbc);
        addView(new JLabel("Genre"), genreLabel, this, gbc);
        setViewFields(albumId);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(0, 2, 0, 0));

        JButton btnEdit = new JButton("EDIT");
        JButton btnDelete = new JButton("DELETE");

        bottomPanel.add(btnEdit);
        bottomPanel.add(btnDelete);

// BUTTONS STYLING
        btnEdit.setBackground(Color.ORANGE);
        btnEdit.setForeground(Color.white);
        btnEdit.setFocusPainted(false);
        btnEdit.setFont(new Font("Courier", Font.BOLD, 10));
        btnEdit.setMargin(new Insets(10, 10, 10, 10));

        btnUpdate.setBackground(Color.GREEN);
        btnUpdate.setForeground(Color.white);
        btnUpdate.setFocusPainted(false);
        btnUpdate.setFont(new Font("Courier", Font.BOLD, 10));
        btnUpdate.setMargin(new Insets(10, 10, 10, 10));

        btnDelete.setBackground(Color.red);
        btnDelete.setForeground(Color.white);
        btnDelete.setFocusPainted(false);
        btnDelete.setFont(new Font("Courier", Font.BOLD, 10));
        btnDelete.setMargin(new Insets(10, 10, 10, 10));

        add(bottomPanel);

        genreCB.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Genre genre = (Genre) genreCB.getSelectedItem();
                selectedId = genre.getId();
            }
        });

//EDIT BUTTON LISTENER
        btnEdit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearPanel();
                addEdit(new JLabel("Album name"), nameTF, (JPanel) getParent(), gbc);
                addEdit(new JLabel("Artist"), artistTF, (JPanel) getParent(), gbc);
                addEdit(new JLabel("Year"), yearTF, (JPanel) getParent(), gbc);
                addEditCB(new JLabel("Year"), genreCB, (JPanel) getParent(), gbc);
                setUpdateFields(albumId);
                add(btnUpdate);
            }
        });

//UPDATE BUTTON LISTENER
        btnUpdate.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                albumStorage.update(setAlbum(albumId));
                JOptionPane.showMessageDialog(getParent(), "Album updated successfully!");
                utils.disposePanel((JPanel) getParent());
                new Frame();
            }
        });

//DELETE BUTTON LISTENER
        btnDelete.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Yes", "No!"};
                int deleteChoice = JOptionPane.showOptionDialog(getParent(), "Do you wand delete this album?",
                        "According", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options,
                        options[0]);
                if (deleteChoice == 0) {
                    albumStorage.delete(albumId);
                    new Frame();
                }
            }
        });
    }

    // Additional methods
    private void addView(JLabel label, JLabel label2, JPanel p, GridBagConstraints gbc) {
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        p.add(label, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        p.add(label2, gbc);
    }

    private void addEdit(JLabel label, JTextField tf, JPanel p, GridBagConstraints gbc) {
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        p.add(label, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        p.add(tf, gbc);
    }

    private void addEditCB(JLabel label, JComboBox cb, JPanel p, GridBagConstraints gbc) {
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        p.add(label, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        p.add(cb, gbc);
    }

    private void setViewFields(int albumId) {
        Album album = (Album) albumStorage.findOnId(albumId);
        nameLabel.setText(album.getName());
        artistLabel.setText(album.getArtist());
        yearLabel.setText(String.valueOf(album.getYear()));
        genreLabel.setText(String.valueOf(genreStorage.findOnId(album.getGenreId())));
    }

    private void clearPanel() {
        Component[] components = getComponents();
        for (Component component : components) {
            remove(component);
        }
        revalidate();
        repaint();
    }

    private void setUpdateFields(int id) {
        Album album = (Album) albumStorage.findOnId(id);
        nameTF.setText(album.getName());
        artistTF.setText(album.getArtist());
        yearTF.setText(String.valueOf(album.getYear()));
        List<Genre> genres = genreStorage.findAll();
        for (Genre g : genres) {
            genreCB.addItem(g);
        }
    }

    private Album setAlbum(int id) {
        Album album = new Album();
        album.setId(id);
        album.setName(nameTF.getText());
        album.setArtist(artistTF.getText());
        album.setYear(Integer.parseInt(yearTF.getText()));
        album.setGenreId(selectedId);
        return album;
    }
}
