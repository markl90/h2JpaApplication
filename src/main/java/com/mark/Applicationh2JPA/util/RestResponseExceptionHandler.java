package com.mark.Applicationh2JPA.util;

import com.mark.Applicationh2JPA.entity.Asset;
import com.mark.Applicationh2JPA.entity.Employee;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by U.8902078 on 21/01/2019.
 */
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler (value = EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Cannot complete request. \nEmployee not found.";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler (value = AssetAllocatedException.class)
    public ResponseEntity<Object> handleAssetConflict(AssetAllocatedException ex,  WebRequest request) {

       // String bodyOfResponse = "Cannot complete request. \nResource already allocated.";
        String bodyOfResponse = String.format("Cannot complete request. \n" +
                        "The asset you are attempting to allocate - %s model - %s \n" +
                        "Has already been allocated to %s employee ID %s", ex.getAsset().getAssetType(),  ex.getAsset().getSerialCode(), ex.getEmployee().getName(),  ex.getEmployee().getEmployeeId());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
