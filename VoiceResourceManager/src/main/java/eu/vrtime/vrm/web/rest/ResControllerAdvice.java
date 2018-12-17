package eu.vrtime.vrm.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import eu.vrtime.vrm.domain.exceptions.*;

import org.h2.jdbc.JdbcSQLException;

public class ResControllerAdvice {

	@ResponseBody
	@ExceptionHandler({ DataNotFoundException.class, ResourceNotFoundException.class,
			SessionManagerNotFoundException.class, VoiceServiceNotFoundException.class,
			SoftswitchNotFoundException.class, NoFreeResourcesException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	StatusCodeExceptionResponse notFoundExceptionHandler(StatusCodeException sce) {
		return new StatusCodeExceptionResponse(sce);
	}

	@ResponseBody
	@ExceptionHandler({ StatusCodeException.class, JdbcSQLException.class })
//	@ExceptionHandler({ StatusCodeException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	StatusCodeExceptionResponse statusCodeExceptionHandler(StatusCodeException sce) {
		return new StatusCodeExceptionResponse(sce);
	}

	public static class StatusCodeExceptionResponse {
		private String statusCode;
		private String statusText;

		public StatusCodeExceptionResponse(StatusCodeException sce) {
			this.statusCode = sce.getStatusCode();
			this.statusText = sce.getMessage();
		}

		public String getStatusCode() {
			return statusCode;
		}

		public String getStatusText() {
			return statusText;
		}
	}

}
