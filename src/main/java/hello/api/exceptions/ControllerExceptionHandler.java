package hello.api.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by Javier on 06/05/2016.
 */
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(UnauthorizedException exception) {
        return handle(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = DataBaseException.class)
    public ResponseEntity<Object> handleDataBaseException(DataBaseException exception) {
        return handle(exception, "Conflicto en la Base de datos.", new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> handleParamsError(BadRequestException exception) {
        return handle(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OKException.class)
    public ResponseEntity<Object> handleOKResponse(OKException exception) {
        return handle(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handle(Exception ex, Object body, HttpHeaders headers, HttpStatus status) {
        return new ResponseEntity<Object>(body, headers, status);
    }
}
