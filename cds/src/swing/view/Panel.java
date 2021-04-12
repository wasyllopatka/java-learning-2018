package swing.view;

import swing.model.AlbumModel;
import swing.model.GenreModel;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Panel extends JPanel {
    private static String source = "XML";
    private AlbumModel albumModel;
    private GenreModel genreModel;
    private JLabel sourceHeader = new JLabel("XML SOURCE");

    public static String getSource() {
        return source;
    }
    private void setSource(String source) {
        Panel.source = source;
    }

    public Panel(String sourceName) {
        if (sourceName != null) {
            setSource(sourceName);
            sourceHeader.setText(sourceName + " SOURCE");
        }
        setLayout(new BorderLayout());
        JPanel headerPanel = new JPanel();
        JPanel subHeaderPanel = new JPanel();
        subHeaderPanel.setLayout(new GridLayout(0, 2, 0, 0));
        headerPanel.setLayout(new BorderLayout());
        ImageIcon homeIcon = new ImageIcon("cd.png");
        JButton btnHome = new JButton(homeIcon);
        JLabel nameHeader = new JLabel("CD COLLECTION");
        JButton btnAlbums = new JButton("Albums");
        JButton btnGenres = new JButton("Genres");
        headerPanel.add(btnHome, BorderLayout.WEST);
        headerPanel.add(sourceHeader, BorderLayout.EAST);
        headerPanel.add(nameHeader, BorderLayout.CENTER);

        subHeaderPanel.add(btnAlbums);
        subHeaderPanel.add(btnGenres);
        headerPanel.add(subHeaderPanel, BorderLayout.SOUTH);
        headerPanel.setBackground(Color.white);

        JPanel buttonsPanel = new JPanel();
        JButton btnAddAlbum = new JButton("+ NEW ALBUM");
        JButton btnAddGenre = new JButton("+ NEW GENRE");
        JLabel sourceLabel = new JLabel("Choose storage source:");
        JButton btnXml = new JButton(" - XML");
        JButton btnJson = new JButton(" - JSON");
        JButton btnPostgres = new JButton(" - POSTGRES");

        buttonsPanel.setLayout(new GridLayout(8, 1, 0, 0));
        buttonsPanel.add(sourceLabel);
        buttonsPanel.add(btnXml);
        buttonsPanel.add(btnJson);
        buttonsPanel.add(btnPostgres);
        buttonsPanel.add(btnAddAlbum);
        buttonsPanel.add(btnAddGenre);

        JTable table = new JTable(new AlbumModel());
        table.setRowHeight(30);
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(252, 243, 207));
        JScrollPane tableScroll = new JScrollPane(table);
        Dimension tablePreferred = tableScroll.getPreferredSize();
        tableScroll.setPreferredSize(new Dimension(tablePreferred.width, tablePreferred.height / 3));
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(100, 40));

        JPanel viewAlbumPanel = new JPanel();
        viewAlbumPanel.add(new AddAlbum());

        JPanel viewGenrePanel = new JPanel();
        viewGenrePanel.add(new AddGenre());

        JPanel viewEditAlbumPanel = new JPanel();

        add(headerPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.WEST);
        add(tableScroll);


        // Buttons Listeners

        btnAlbums.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                albumModel = new AlbumModel();
                table.setModel(albumModel);
            }
        });

        btnGenres.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genreModel = new GenreModel();
                table.setModel(genreModel);
            }
        });

        btnXml.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSource("XML");
                sourceHeader.setText("XML SOURCE");
                albumModel = new AlbumModel();
                table.setModel(albumModel);
                revalidate();
                repaint();
            }
        });

        btnJson.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSource("JSON");
                sourceHeader.setText("JSON SOURCE");
                albumModel = new AlbumModel();
                table.setModel(albumModel);
                revalidate();
                repaint();
            }
        });

        btnPostgres.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSource("POSTGRES");
                sourceHeader.setText("POSTGRES SOURCE");
                albumModel = new AlbumModel();
                table.setModel(albumModel);
                revalidate();
                repaint();
            }
        });

        btnAddAlbum.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(tableScroll);
                remove(buttonsPanel);
                headerPanel.remove(subHeaderPanel);
                add(viewAlbumPanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        btnAddGenre.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(tableScroll);
                remove(buttonsPanel);
                headerPanel.remove(subHeaderPanel);
                add(viewGenrePanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        btnHome.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sourceName = Panel.getSource();
                removeAll();
                add(new Panel(sourceName));
                table.setModel(new AlbumModel());
                revalidate();
                repaint();
            }
        });

        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    int column = 0;
                    String value = table.getModel().getValueAt(row, column).toString();
                    int albumId = Integer.parseInt(value);
                    remove(tableScroll);
                    remove(buttonsPanel);
                    headerPanel.remove(subHeaderPanel);
                    viewEditAlbumPanel.add(new ViewEditAlbum(albumId));
                    add(viewEditAlbumPanel);
                    revalidate();
                    repaint();
                }
            }
        });

        // Elements styling

        sourceHeader.setFont(new Font("Courier", Font.BOLD, 25));
        sourceHeader.setForeground(Color.red);

        nameHeader.setFont(new Font("Courier", Font.BOLD, 25));
        nameHeader.setForeground(Color.black);

        btnAddAlbum.setBackground(new Color(41, 139, 71));
        btnAddAlbum.setFocusPainted(false);
        btnAddAlbum.setFont(new Font("Courier", Font.BOLD, 10));

        btnAddGenre.setBackground(new Color(41, 139, 71));
        btnAddGenre.setFocusPainted(false);
        btnAddGenre.setFont(new Font("Courier", Font.BOLD, 10));

        btnHome.setFocusPainted(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setBorderPainted(false);

        btnAlbums.setBackground(new Color(52, 73, 94));
        btnAlbums.setForeground(Color.white);
        btnAlbums.setFocusPainted(false);
        btnAlbums.setFont(new Font("Courier", Font.BOLD, 15));
        btnAlbums.setMargin(new Insets(10, 10, 10, 10));

        btnGenres.setBackground(new Color(93, 109, 126));
        btnGenres.setForeground(Color.white);
        btnGenres.setFocusPainted(false);
        btnGenres.setFont(new Font("Courier", Font.BOLD, 15));
        btnGenres.setMargin(new Insets(10, 10, 10, 10));

        sourceLabel.setFont(new Font("Courier", Font.BOLD, 12));
        sourceLabel.setBackground(Color.white);

        btnXml.setBackground(new Color(21, 67, 96));
        btnXml.setForeground(Color.white);
        btnXml.setFocusPainted(false);
        btnXml.setFont(new Font("Courier", Font.BOLD, 10));
        btnXml.setMargin(new Insets(10, 10, 10, 10));

        btnJson.setBackground(new Color(21, 67, 96));
        btnJson.setForeground(Color.white);
        btnJson.setFocusPainted(false);
        btnJson.setFont(new Font("Courier", Font.BOLD, 10));
        btnJson.setMargin(new Insets(10, 10, 10, 10));

        btnPostgres.setBackground(new Color(21, 67, 96));
        btnPostgres.setForeground(Color.white);
        btnPostgres.setFocusPainted(false);
        btnPostgres.setFont(new Font("Courier", Font.BOLD, 10));
        btnPostgres.setMargin(new Insets(10, 10, 10, 10));
    }
}
