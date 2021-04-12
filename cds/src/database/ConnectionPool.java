package database;

import java.sql.Connection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class ConnectionPool {
    private BlockingQueue<Connection> connections;
    private final int POOL_SIZE = 5;
    private static Lock lock = new ReentrantLock(true);
    private static ConnectionPool connectionPool;
    private static AtomicBoolean flag = new AtomicBoolean(true);
    private final static Logger LOGGER = Logger.getLogger(ConnectionPool.class.getName());

    private ConnectionPool() {
        connections = new ArrayBlockingQueue<>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {
            Connection connection = ConnectorDB.getConnection();
            connections.offer(connection);
        }
    }

    public static ConnectionPool getInstance() {
        if (flag.get()) {
            lock.lock();
            try {
                if (connectionPool == null) {
                    connectionPool = new ConnectionPool();
                    flag.set(false);
                }
            } finally {
                lock.unlock();
            }
        }
        return connectionPool;
    }

        public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            LOGGER.info("Thread is interrupted");
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            connections.offer(connection);
//            LOGGER.info("Connection " + connection + " released");
        }
    }
}