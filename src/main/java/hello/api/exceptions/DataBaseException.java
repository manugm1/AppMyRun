package hello.api.exceptions;

/**
 * Created by Javier on 18/05/2016.
 */
public class DataBaseException extends Exception {

    public DataBaseException(String message) {
        super(message);
    }

    public DataBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBaseException(Throwable cause) {
        super(cause);
    }
}
