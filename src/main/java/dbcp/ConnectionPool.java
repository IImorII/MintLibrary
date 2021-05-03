package dbcp;

import context.AppContext;
import exception.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConnectionPool {
    private static ConnectionPool INSTANCE;
    private final String URL = AppContext.getURL();
    private final String LOGIN = AppContext.getLOGIN();
    private final String PASSWORD = AppContext.getPASSWORD();
    private final Integer MAX_POOL_SIZE = AppContext.getMaxPoolSize();
    private final Integer INIT_POOL_SIZE = AppContext.getInitPoolSize();

    private List<Connection> freeConnections = new CopyOnWriteArrayList<>();
    private List<Connection> busyConnections = new CopyOnWriteArrayList<>();

    private ConnectionPool() {
        init();
    }

    public static ConnectionPool getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionPool();
        }
        return INSTANCE;
    }

    public void init() {
        for (int i = 0; i < INIT_POOL_SIZE; i++) {
            try(Connection newConnection = new ConnectionProxy(DriverManager.getConnection(URL, LOGIN, PASSWORD))) {
                freeConnections.add(newConnection);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        if (freeConnections.isEmpty()) {
            init();
        } else {
            busyConnections.add(freeConnections.get(0));
            freeConnections.remove(0);
        }
        return busyConnections.get(busyConnections.size() - 1);
    }

    public void releaseConnection(Connection connection) throws ConnectionException {
        freeConnections.add(connection);
        busyConnections.remove(connection);
    }
}
