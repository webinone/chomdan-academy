package com.chomdan.shared.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by foresight on 17. 7. 31.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<RestResultData> handleSQLException(HttpServletRequest request, Exception ex){
        logger.info("SQLException Occured:: URL="+request.getRequestURL());

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(false, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<RestResultData> handleResourceNotFoundException(HttpServletRequest request, Exception ex){
        logger.info("ResourceNotFoundException Occured:: URL="+request.getRequestURL());

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(false, ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = FileNotFoundException.class)
    public ResponseEntity<RestResultData> handleFileNotFoundException(HttpServletRequest request, Exception ex){
        logger.info("FileNotFoundException Occured:: URL="+request.getRequestURL());
        
        logger.error("error message : " + ex.getMessage());

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(false, ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }
//
//    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="IOException occured")
//    @ExceptionHandler(IOException.class)
//    public void handleIOException(){
//        logger.error("IOException handler executed");
//        //returning 404 error code
//    }


//    @ExceptionHandler(EmployeeNotFoundException.class)
//    public @ResponseBody ExceptionJSONInfo handleEmployeeNotFoundException(HttpServletRequest request, Exception ex){
//
//        ExceptionJSONInfo response = new ExceptionJSONInfo();
//        response.setUrl(request.getRequestURL().toString());
//        response.setMessage(ex.getMessage());
//
//        return response;
//    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<RestResultData> handleException (HttpServletRequest request, Exception ex) {
//
//        RestResultData restResultData = new RestResultData();
//
//        restResultData.setSuccess(false);
//        restResultData.setResultCode(Http);
//    }
}
