package swing.model;

import model.Genre;
import storage.AbstractStorage;
import storage.StorageFactory;
import swing.view.Panel;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class GenreModel extends AbstractTableModel {
    private AbstractStorage genreStorage;
    private List<Genre> genres;
    private String[] headers = {"genre_id", "name"};

    public GenreModel() {
        super();
        StorageFactory storageFactory = new StorageFactory();
        List<AbstractStorage> storages = storageFactory.getStorage(Panel.getSource());
        genreStorage = storages.get(1);
        genres = genreStorage.findAll();
    }

    @Override
    public int getRowCount() {
        return genres.size();
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
                return genres.get(rowIndex).getId();
            case 1:
                return genres.get(rowIndex).getName();
            default:
                return genres;
        }
    }
}
