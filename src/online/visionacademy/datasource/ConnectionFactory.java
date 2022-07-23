package online.visionacademy.datasource;

import online.visionacademy.exceptions.DataSourceException;
import java.sql.Connection;

public abstract class ConnectionFactory {

    public abstract String getURL();

    public abstract String getUser();

    public abstract String getPassword();

    public abstract String getDriverName();

    public abstract Connection createConnection() throws DataSourceException;

    public static ConnectionFactory getConnectionFactory(DataSourceType factory) {
        switch (factory) {
            case MYSQL:
                return new MySQLConnectionFactory();
            case ORACLE:
                return null;
            case SYBASE:
                return null;
            default:
               return null;
        }

    }
}
