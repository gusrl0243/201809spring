package kr.or.ddit.util.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import kr.or.ddit.user.model.UserVo;

@ControllerAdvice
public class CommonExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(UserVo.class);
	
	 @ExceptionHandler({ArithmeticException.class})
	   public String handelException(){
		   logger.debug("CommonExceptionHandler");
		   return "error/error";
	   }
}
