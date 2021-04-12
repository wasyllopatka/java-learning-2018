package storage.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import model.Genre;
import storage.AbstractStorage;
import storage.Constants;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JsonGenreStorage implements AbstractStorage<Genre> {
    @Override
    public List<Genre> findAll() {
        ObjectMapper mapper = new ObjectMapper();
        List<Genre> genres = new ArrayList<>();
        try {
            genres = mapper.readValue(
                    new File(Constants.JSON_GENRE_PATH),
                    new TypeReference<List<Genre>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return genres;
    }

    @Override
    public Genre findOnId(Integer id) {
        List<Genre> genres = findAll();
        Genre genre = new Genre();
        for (Genre g : genres) {
            if (g.getId() == id) {
                genre = g;
                break;
            }
        }
        return genre;
    }

    @Override
    public Genre add(Genre entity) {
        List<Genre> genres = findAll();
        Genre element = Collections.max(genres, Comparator.comparingInt(Genre::getId));
        int maxId = element.getId();
        entity.setId(maxId + 1);
        genres.add(entity);
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(new File(Constants.JSON_GENRE_PATH), genres);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void update(Genre entity) {
        List<Genre> genres = findAll();
        for (Genre genre : genres) {
            if (genre != null && entity.getId() == genre.getId()) {
                genre.setName(entity.getName());
                break;
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(new File(Constants.JSON_GENRE_PATH), genres);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        List<Genre> genres = findAll();
        for (Genre genre : genres) {
            if (genre != null && id == genre.getId()) {
                genres.remove(genre);
                break;
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(new File(Constants.JSON_GENRE_PATH), genres);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
