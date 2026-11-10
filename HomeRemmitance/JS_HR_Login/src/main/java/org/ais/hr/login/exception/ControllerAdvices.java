package org.ais.hr.login.exception;


import org.ais.hr.login.dto.FieldErrorVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvices extends ResponseEntityExceptionHandler  {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvices.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid( MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders headers, HttpStatus status,
                                                                   WebRequest request) {

        BindingResult result = methodArgumentNotValidException.getBindingResult();
        List<FieldErrorVM> fieldErrors = result.getFieldErrors().stream()
                .map(f -> new FieldErrorVM(f.getField(), f.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ResponseEntity(fieldErrors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object>handleIllegalArgumentException(IllegalArgumentException exception,
                                                                       WebRequest webRequest) {
        final String ERROR_MESSAGE = exception.getMessage();
        logger.error(ERROR_MESSAGE, exception);
        ErrorMessage errorResponse = getErrorResponse(HttpStatus.BAD_REQUEST, ERROR_MESSAGE);
        return handleExceptionInternal(exception, errorResponse, new HttpHeaders(),
                HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object>handleAnyException(Exception exception, WebRequest webRequest) {
        final String ERROR_MESSAGE = "An unexpected error occurred";
        logger.error(ERROR_MESSAGE, exception);
        ErrorMessage errorResponse = getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_MESSAGE);
        return handleExceptionInternal(exception, errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
                webRequest);
    }

    private ErrorMessage getErrorResponse(HttpStatus status, String errorMessage) {
        if (StringUtils.isEmpty(errorMessage)) {
            errorMessage = "An unexpected error occurred";
        }
        return new ErrorMessage(status.value(), status.name(), errorMessage);
    }



    }
    //    @ExceptionHandler(NoDataFoundException.class)
//    public ResponseEntity<ErrorMessage> resourceNotFoundException(NoDataFoundException ex, WebRequest request) {
//        ErrorMessage message = new ErrorMessage(
//                HttpStatus.NOT_FOUND.value(),
//                new Date(),
//                ex.getMessage(),
//                request.getDescription(false));
//
//        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
//        ErrorMessage message = new ErrorMessage(
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                new Date(),
//                ex.getMessage(),
//                request.getDescription(false));
//
//        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

