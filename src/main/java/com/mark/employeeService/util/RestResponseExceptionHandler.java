package com.mark.employeeService.util;

import com.mark.employeeService.util.exceptions.AssetAllocatedException;
import com.mark.employeeService.util.exceptions.EmployeeNotFoundException;
import com.mark.employeeService.util.exceptions.UndefinedSearchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private class ErrorMessage {
        private final String error;
        private final String errorMessage;
        private final int status;

        public ErrorMessage(String errorMessage, String error, int status) {
            this.errorMessage = errorMessage;
            this.error = error;
            this.status = status;
        }

        public String getError() {
            return error;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public int getStatus() {
            return status;
        }
    }

    @ExceptionHandler (value = EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage("Cannot complete request", "Employee not found.", 404);
        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler (value = AssetAllocatedException.class)
    public ResponseEntity<Object> handleAssetConflict(AssetAllocatedException ex,  WebRequest request) {
        String bodyOfResponse = String.format("The asset you are attempting to allocate - %s model - %s \n" +
                        "Has already been allocated to %s employee ID %s", ex.getAsset().getAssetType(),  ex.getAsset().getSerialCode(), ex.getEmployee().getName(),  ex.getEmployee().getEmployeeId());
        ErrorMessage errorMessage = new ErrorMessage(bodyOfResponse,"Cannot complete request", 409);
        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler (value = UndefinedSearchException.class)
    public ResponseEntity<Object> handleUndefinedSearchConflict(RuntimeException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage("Search is too broad, please be more specific.","Cannot complete request", 400);
        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
