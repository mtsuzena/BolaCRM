package back.bolaCRM.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BusinessException.class)
    public final ValidationErrorResponse handleAllBusinessExceptions(BusinessException ex, WebRequest request) {
		ValidationErrorResponse error = new ValidationErrorResponse();
		error.setCode(ex.getCode());
		error.setMessage(ex.getMessage());
        return error;
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public final ValidationErrorResponse EmailInvalido(MethodArgumentNotValidException ex, WebRequest request) {
		ValidationErrorResponse error = new ValidationErrorResponse();
		error.setCode(BusinessExceptionCode.ERR003);
		error.setMessage(BusinessExceptionCode.ERR003.getMessage());
        return error;
    }

	@ExceptionHandler(Exception.class)
    public final ValidationErrorResponse handleAllExceptions(Exception ex, WebRequest request) {
		ValidationErrorResponse error = new ValidationErrorResponse();
		error.setCode(BusinessExceptionCode.ERR300);
		error.setMessage(ex.getMessage());
        return error;
    }
	

}