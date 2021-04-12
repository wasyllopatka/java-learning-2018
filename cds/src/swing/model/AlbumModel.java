package swing.model;

import model.Album;
import storage.AbstractStorage;
import storage.StorageFactory;
import swing.view.Panel;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AlbumModel extends AbstractTableModel {
    private List<Album> albums;
    private AbstractStorage albumStorage;
    private AbstractStorage genreStorage;
    private String[] headers = {"id", "name", "artist", "year", "genre"};

    public AlbumModel() {
        super();
        StorageFactory storageFactory = new StorageFactory();
        List<AbstractStorage> storages = storageFactory.getStorage(Panel.getSource());
        albumStorage = storages.get(0);
        genreStorage = storages.get(1);
        albums = albumStorage.findAll();
    }

    @Override
    public int getRowCount() {
        return albums.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    public String getColumnName(int columnIndex) {
        return headers[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return albums.get(rowIndex).getId();
            case 1:
                return albums.get(rowIndex).getName();
            case 2:
                return albums.get(rowIndex).getArtist();
            case 3:
                return albums.get(rowIndex).getYear();
            case 4:
                return genreStorage.findOnId(albums.get(rowIndex).getGenreId());
            default:
                return null;
        }
    }
}
