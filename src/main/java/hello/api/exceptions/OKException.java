package hello.api.exceptions;

/**
 * Created by manuelgm on 16/05/2016.
 */
public class OKException extends Exception{

    public OKException(String message) {
        super(message);
    }

    public OKException(String message, Throwable cause) {
        super(message, cause);
    }

    public OKException(Throwable cause) {
        super(cause);
    }
}
