package storage.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import model.Album;
import storage.AbstractStorage;
import storage.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JsonAlbumStorage implements AbstractStorage<Album> {

    @Override
    public List<Album> findAll() {
        ObjectMapper mapper = new ObjectMapper();
        List<Album> albums = new ArrayList<>();
        try {
            albums = mapper.readValue(
                    new File(Constants.JSON_ALBUM_PATH),
                    new TypeReference<List<Album>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return albums;
    }

    @Override
    public Album findOnId(Integer id) {
        List<Album> albums = findAll();
        Album album = new Album();
        for (Album a : albums) {
            if (a.getId() == id) {
                album = a;
                break;
            }
        }
        return album;
    }

    @Override
    public Album add(Album entity) {
        List<Album> albums = findAll();
        Album element = Collections.max(albums, Comparator.comparingInt(Album::getId));
        int maxId = element.getId();
        entity.setId(maxId + 1);
        albums.add(entity);
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(new File(Constants.JSON_ALBUM_PATH), albums);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void update(Album entity) {
        List<Album> albums = findAll();
        for (Album album : albums) {
            if (album != null && entity.getId() == album.getId()) {
                album.setName(entity.getName());
                album.setArtist(entity.getArtist());
                album.setYear(entity.getYear());
                album.setGenreId(entity.getGenreId());
                break;
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(new File(Constants.JSON_ALBUM_PATH), albums);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        List<Album> albums = findAll();
        for (Album album : albums) {
            if (album != null && id == album.getId()) {
                albums.remove(album);
                break;
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(new File(Constants.JSON_ALBUM_PATH), albums);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
