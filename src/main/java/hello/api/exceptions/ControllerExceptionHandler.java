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
        return handle(exception, "Invalid access.", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handle(Exception ex, Object body, HttpHeaders headers, HttpStatus status) {
        return new ResponseEntity<Object>(body, headers, status);
    }
}
