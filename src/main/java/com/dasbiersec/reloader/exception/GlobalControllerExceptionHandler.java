package com.dasbiersec.reloader.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ControllerAdvice
public class GlobalControllerExceptionHandler
{
	private Logger log = Logger.getLogger(getClass());

	@ExceptionHandler({IOException.class})
	@ResponseBody
	public ErrorMessage handleException(IOException e)
	{
		log.debug("Running handleException");
		ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
		return errorMessage;
	}
}
