package storage;

import storage.json.JsonAlbumStorage;
import storage.json.JsonGenreStorage;
import storage.postgres.PostgresAlbumStorage;
import storage.postgres.PostgresGenreStorage;
import storage.xml.XmlAlbumStorage;
import storage.xml.XmlGenreStorage;

import java.util.ArrayList;
import java.util.List;

public class StorageFactory {
    public List<AbstractStorage> getStorage(String storageType) {
        if (storageType == null) {
            return null;
        }
        if (storageType.equalsIgnoreCase("POSTGRES")) {
            List<AbstractStorage> storages = new ArrayList<>();
            storages.add(new PostgresAlbumStorage());
            storages.add(new PostgresGenreStorage());
            return storages;
        }

        if (storageType.equalsIgnoreCase("XML")) {
            List<AbstractStorage> storages = new ArrayList<>();
            storages.add(new XmlAlbumStorage());
            storages.add(new XmlGenreStorage());
            return storages;
        }

        if (storageType.equalsIgnoreCase("JSON")) {
            List<AbstractStorage> storages = new ArrayList<>();
            storages.add(new JsonAlbumStorage());
            storages.add(new JsonGenreStorage());
            return storages;
        }
        return null;
    }
}
