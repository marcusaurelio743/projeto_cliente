package clientes.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import clientes.rest.exeption.ApiErros;

@RestControllerAdvice
public class ApplicationcontrollerAdvice {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErros hadValidationErros(MethodArgumentNotValidException ex) {
		BindingResult bindingResult= ex.getBindingResult();
		 List<String> mensagens = bindingResult.getAllErrors()
		.stream()
		.map(ObjectError ->ObjectError.getDefaultMessage())
		.collect(Collectors.toList());
		 return new ApiErros(mensagens);
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<?> hadleresponseexeption(ResponseStatusException ex) {
		String MessageError = ex.getMessage();
		HttpStatus codigoStatus = ex.getStatus();
		ApiErros apiErros = new ApiErros(MessageError);
		return new ResponseEntity(apiErros, codigoStatus);
		
	}

}
