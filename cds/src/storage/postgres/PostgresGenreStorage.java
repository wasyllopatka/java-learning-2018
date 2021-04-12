package storage.postgres;

import database.ConnectionPool;
import model.Genre;
import storage.AbstractStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresGenreStorage implements AbstractStorage<Genre> {
    private static final String SELECT_ALL_FROM_GENRES = "SELECT * FROM genre";
    private static final String SELECT_ONE_FROM_GENRES = "SELECT * FROM genre WHERE id = ?";
    private static final String CREATE_NEW_GENRE = "INSERT INTO genre (id, g_name) VALUES (nextval('genre_sequence'), ?, ?)";
    private static final String DELETE_GENRE_ON_ID = "DELETE FROM genre WHERE id = ?";

    @Override
    public List<Genre> findAll() {
        List<Genre> genres = new ArrayList<>();
        ConnectionPool connectionPool = null;
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_ALL_FROM_GENRES);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Genre genre = new Genre();
                genre.setId(resultSet.getInt("id"));
                genre.setName(resultSet.getString("g_name"));
                genres.add(genre);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connectionPool != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return genres;
    }

    @Override
    public Genre findOnId(Integer id) {
        ConnectionPool connectionPool = null;
        PreparedStatement statement = null;
        Connection connection = null;
        Genre genre = new Genre();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_ONE_FROM_GENRES);
            statement.setInt(1, id);
            genre = setGenreData(statement);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connectionPool != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return genre;
    }

    @Override
    public Genre add(Genre entity) {
        ConnectionPool connectionPool = null;
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(CREATE_NEW_GENRE);
            statement.setString(1, String.valueOf(entity.getId()));
            statement.setString(1, entity.getName());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connectionPool != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return entity;
    }

    @Override
    public void update(Genre entity) {
    }

    @Override
    public void delete(int id) {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_GENRE_ON_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connectionPool != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    public Genre setGenreData(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        Genre genre = new Genre();
        while (resultSet.next()) {
            genre.setId(Integer.parseInt(resultSet.getString("id")));
            genre.setName(resultSet.getString("g_name"));
        }
        resultSet.close();
        return genre;
    }
}
