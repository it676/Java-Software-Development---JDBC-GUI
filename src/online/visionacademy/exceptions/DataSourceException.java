package online.visionacademy.exceptions;

public class DataSourceException extends Exception {

    public DataSourceException(String message) {
        super(message);
    }
    
    public DataSourceException(String message, Throwable cause){
        super(message, cause);
    }
}
