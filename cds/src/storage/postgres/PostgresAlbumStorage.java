package storage.postgres;

import database.ConnectionPool;
import model.Album;
import storage.AbstractStorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresAlbumStorage implements AbstractStorage<Album> {
    private static final String SELECT_ALL_FROM_ALBUMS = "SELECT a.id, a.a_name, a.artist, a.a_year, a.genreId, g.g_name " + "FROM album a JOIN genre g on a.genreId = g.id;";
    private static final String SELECT_ONE_FROM_ALBUMS = "SELECT * FROM genre INNER JOIN album ON genre.id = album.genreId WHERE album.id = ?";
    private static final String CREATE_NEW_ALBUM = "INSERT INTO album (id, a_name, artist, a_year, genreId) VALUES (nextval('album_sequence'), ?, ?, ?, ?)";
    private static final String UPDATE_ALBUM = "UPDATE album SET a_name = ?, artist = ?, a_year = ?, genreId = ? WHERE id = ?";
    private static final String DELETE_ALBUM_ON_ID = "DELETE FROM album WHERE id = ?";

    @Override
    public List<Album> findAll() {
        List<Album> albums = new ArrayList<>();
        ConnectionPool connectionPool = null;
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_ALL_FROM_ALBUMS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Album album = new Album();
                album.setId(resultSet.getInt("id"));
                album.setName(resultSet.getString("a_name"));
                album.setArtist(resultSet.getString("artist"));
                album.setYear(Integer.parseInt(resultSet.getString("a_year")));
                album.setGenreId(Integer.parseInt(resultSet.getString("genreId")));
                albums.add(album);
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
        return albums;
    }

    @Override
    public Album findOnId(Integer id) {
        ConnectionPool connectionPool = null;
        PreparedStatement statement = null;
        Connection connection = null;
        Album album = new Album();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_ONE_FROM_ALBUMS);
            statement.setInt(1, id);
            album = setAlbumData(statement);
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
        return album;
    }

    @Override
    public Album add(Album entity) {
        ConnectionPool connectionPool = null;
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(CREATE_NEW_ALBUM);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getArtist());
            statement.setInt(3, entity.getYear());
            statement.setInt(4, entity.getGenreId());
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
    public void update(Album entity) {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement s = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            s = connection.prepareStatement(UPDATE_ALBUM);
            s.setString(1, entity.getName());
            s.setString(2, entity.getArtist());
            s.setInt(3, entity.getYear());
            s.setInt(4, entity.getGenreId());
            s.setInt(5, entity.getId());
            s.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (s != null) {
                    s.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connectionPool != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void delete(int id) {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_ALBUM_ON_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connectionPool != null) {
                    connectionPool.releaseConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public Album setAlbumData(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        Album album = new Album();
        while (resultSet.next()) {
            album.setName(resultSet.getString("a_name"));
            album.setArtist(resultSet.getString("artist"));
            album.setYear(Integer.parseInt(resultSet.getString("a_year")));
            album.setGenreId(Integer.parseInt(resultSet.getString("genreId")));
        }
        resultSet.close();
        return album;
    }
}
