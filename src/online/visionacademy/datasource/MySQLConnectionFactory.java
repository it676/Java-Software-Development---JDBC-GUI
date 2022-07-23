package online.visionacademy.datasource;

import online.visionacademy.common.PropertiesReader;
import online.visionacademy.exceptions.DataSourceException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionFactory extends ConnectionFactory {

    private static final String CONFIG_FILE = "src/resources/mysql.properties";
    private static final String DRIVER_NAME = "MySQL";
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    private static final String ERROR_MSG = "Can't connect to the DB, please check your credentials";

    private static final PropertiesReader READER;

    static {
        READER = new PropertiesReader(CONFIG_FILE);
        URL = READER.get("url");
        USER = READER.get("user");
        PASSWORD = READER.get("password");
    }

    @Override
    public String getURL() {
        return URL;
    }

    @Override
    public String getUser() {
        return USER;
    }

    @Override
    public String getPassword() {
        return PASSWORD;
    }

    @Override
    public String getDriverName() {
        return DRIVER_NAME;
    }

    @Override
    public Connection createConnection() throws DataSourceException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            throw new DataSourceException(ERROR_MSG, ex);
        }
    }
}
