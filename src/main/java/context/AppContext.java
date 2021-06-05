package context;

import util.PropertyReaderUtil;
import java.util.Properties;

public class AppContext {
    private static String URL;
    private static String LOGIN;
    private static String PASSWORD;
    private static Integer INIT_POOL_SIZE;
    private static Integer MAX_POOL_SIZE;

    public static String getURL() {
        if (URL == null) {
            init();
        }
        return URL;
    }

    public static String getLogin() {
        if (LOGIN == null) {
            init();
        }
        return LOGIN;
    }

    public static String getPassword() {
        if (PASSWORD == null) {
            init();
        }
        return PASSWORD;
    }

    public static Integer getInitPoolSize() {
        if (INIT_POOL_SIZE == null) {
            init();
        }
        return INIT_POOL_SIZE;
    }

    public static Integer getMaxPoolSize() {
        if (MAX_POOL_SIZE == null) {
            init();
        }
        return MAX_POOL_SIZE;
    }

    public static void init() {
        Properties properties = PropertyReaderUtil.getProperties();
        URL = properties.getProperty("db.url");
        LOGIN = properties.getProperty("db.login");
        PASSWORD = properties.getProperty("db.password");
        INIT_POOL_SIZE = Integer.parseInt(properties.getProperty("db.initpoolsize"));
        MAX_POOL_SIZE = Integer.parseInt(properties.getProperty("db.maxpoolsize"));
    }
}
