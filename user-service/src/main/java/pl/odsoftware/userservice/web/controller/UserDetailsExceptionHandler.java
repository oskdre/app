package pl.odsoftware.userservice.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.odsoftware.userservice.infrastructure.database.LoginCounterIncrementException;
import pl.odsoftware.userservice.infrastructure.rest.UnexpectedServerResponse;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Slf4j
public class UserDetailsExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleIncorrectRequestParams(ConstraintViolationException ex){
        log.error("Incorrect input parameters", ex);
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(value = {DataAccessException.class, LoginCounterIncrementException.class})
    public ResponseEntity<Object>  handleDataAccessErrors(DataAccessException ex){
        log.error("Error on data access", ex);
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(value = {UnexpectedServerResponse.class})
    public ResponseEntity<Object>  handleExternalServiceErrors(UnexpectedServerResponse ex){
        log.error("Unexpected external service response", ex);
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object>  handleOthers(Exception ex){
        log.error("Exception was thrown", ex);
        return ResponseEntity.internalServerError().build();
    }


}
