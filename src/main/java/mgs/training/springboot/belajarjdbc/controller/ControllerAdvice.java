package mgs.training.springboot.belajarjdbc.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import mgs.training.springboot.belajarjdbc.constant.ErrorCode;
import mgs.training.springboot.belajarjdbc.dto.CustomException;
import mgs.training.springboot.belajarjdbc.dto.http.HttpRespModel;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

	@ExceptionHandler
	public ResponseEntity<Object> handle(MethodArgumentNotValidException exception) {
		// you will get all javax failed validation, can be more than one
		// so you can return the set of error messages or just the first message
		String errMessage = exception.getBindingResult().getFieldError().getDefaultMessage();
		return ResponseEntity.ok().body(HttpRespModel.error(ErrorCode.REQUIRED, errMessage));
	}
	
	@ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(Exception ex) {
        log.error("!!! Caught exception : {}", ExceptionUtils.getStackTrace(ex));
        
        String exceptionMsg = StringUtils.isNotBlank(ex.getMessage()) ? ex.getMessage() : "Undefined Error";
        if(ex.getCause()!=null && ex.getCause() instanceof CustomException
        		&& StringUtils.isNotBlank(ex.getCause().getMessage())) {
        	exceptionMsg = ex.getCause().getMessage();
        }
        
		String messages[] = exceptionMsg.split("\\|");
        Integer errCode = ErrorCode.ERROR_OTHER;
        String errDesc = "System Error.";
        
        if(messages.length > 1) {
        	errCode = Integer.parseInt(messages[0]);
        	errDesc = ex.getMessage().substring(ex.getMessage().indexOf("|")+1);
		}
        return ResponseEntity.ok().body(HttpRespModel.error(errCode, errDesc));
    }
}
