package dbcp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private static ConnectionPool INSTANCE;
    private final int POOL_MAX_SIZE = 10;

    private List<Connection> pool = new ArrayList<>(POOL_MAX_SIZE);

    private ConnectionPool() {

    }

    public static ConnectionPool getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionPool();
        }
        return INSTANCE;
    }

    public void init() {
        for (int i = 0; i < POOL_MAX_SIZE; i++) {
            try(Connection newConnection = new ConnectionProxy(DriverManager.getConnection(""))) {
                pool.add(newConnection);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public Connection getFromPool() {
        if (pool.isEmpty()) {
            init();
        }
        return pool.get(0);
    }
}
