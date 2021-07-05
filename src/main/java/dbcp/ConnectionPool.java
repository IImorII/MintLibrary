package dbcp;

import context.AppContext;
import exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger log = LogManager.getLogger(ConnectionPool.class);
    private static final String URL = AppContext.getURL();
    private static final String LOGIN = AppContext.getLogin();
    private static final String PASSWORD = AppContext.getPassword();
    private static final String NAME = AppContext.getName();
    private static final String DRIVER = AppContext.getDriver();
    private static final Integer MAX_POOL_SIZE = AppContext.getMaxPoolSize();
    private static final Integer INIT_POOL_SIZE = AppContext.getInitPoolSize();

    private static final Lock LOCK_INSTANCE = new ReentrantLock();
    private static final Lock LOCK_CONNECTION = new ReentrantLock();

    private static final AtomicBoolean isInitialized = new AtomicBoolean(false);

    private final List<Connection> freeConnections = new CopyOnWriteArrayList<>();
    private final List<Connection> busyConnections = new CopyOnWriteArrayList<>();

    private ConnectionPool() {

    }

    private static class ConnectionPoolHolder {
        private static final ConnectionPool HOLDER_INSTANCE = new ConnectionPool();
    }

    public static ConnectionPool getInstance() {
        if (!isInitialized.get()) {
            try {
                LOCK_INSTANCE.lock();
                if (!isInitialized.get()) {
                    ConnectionPoolHolder.HOLDER_INSTANCE.init();
                }
            } catch (Exception ex) {
                log.error(ex.getMessage());
                ex.printStackTrace();
            } finally {
                LOCK_INSTANCE.unlock();
            }
        }
        return ConnectionPoolHolder.HOLDER_INSTANCE;
    }

    public void init() {
        log.info("Start init connection pool.");
        registerDrivers();
        for (int i = 0; i < INIT_POOL_SIZE; i++) {
            try (Connection newConnection = new ConnectionProxy(DriverManager.getConnection(URL + NAME, LOGIN, PASSWORD))) {
                freeConnections.add(newConnection);
            } catch (SQLException ex) {
                log.error(ex.getMessage());
            }
        }
        isInitialized.set(true);
        log.info("End init connection pool.");
    }

    public Connection getConnection() {
        try {
            LOCK_CONNECTION.lock();
            if (freeConnections.isEmpty()) {
                init();
            } else {
                busyConnections.add(freeConnections.get(0));
                freeConnections.remove(0);
            }
            return busyConnections.get(busyConnections.size() - 1);
        } finally {
            LOCK_CONNECTION.unlock();
        }
    }

    private static void registerDrivers() {
        try {
            log.info("Start register drivers.");
            Class.forName(DRIVER);
            DriverManager.registerDriver(DriverManager.getDriver(URL + NAME));
        } catch (SQLException | ClassNotFoundException e) {
            log.error("Cannot register drivers.");
        }
    }

    public void releaseConnection(Connection connection) throws ConnectionException {
        try {
            LOCK_CONNECTION.lock();
            if (busyConnections.remove(connection)) {
                freeConnections.add(connection);
            } else {
                throw new ConnectionException("Connection is interrupted or not busy.");
            }
        } finally {
            LOCK_CONNECTION.unlock();
        }
    }
}
