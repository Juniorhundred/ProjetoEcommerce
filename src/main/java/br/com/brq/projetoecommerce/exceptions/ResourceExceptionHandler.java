package br.com.brq.projetoecommerce.exceptions;

import java.util.Date;
import java.util.List;
<<<<<<< Updated upstream
=======
import java.util.NoSuchElementException;
>>>>>>> Stashed changes

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javassist.tools.rmi.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgumentNotValidException
			(MethodArgumentNotValidException e, HttpServletRequest request){
		
		ValidationError ve = new ValidationError(
=======
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException error, HttpServletRequest request){
		
		ValidationError validation = new ValidationError(
>>>>>>> Stashed changes
				new Date(),
				HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Erro de Validação",
				"Erro de validação ao salvar/alterar a entidade",
				request.getRequestURI()
				);
		
<<<<<<< Updated upstream
		List<FieldError> errors = e.getBindingResult().getFieldErrors();
		
		for (FieldError err : errors) {
			ve.addError(err.getField(), err.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ve);
	}
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> noSuchElementException(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError se = StandardError
				.builder()
				.timestamp(new Date())
				.status(HttpStatus.NOT_FOUND.value())
				.error("Erro ao achar a entidade")
				.message(e.getMessage())
				.path(request.getRequestURI())
				.build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(se); 
	}
	
	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<StandardError> objetoNaoEncontradoException(ObjetoNaoEncontradoException e, HttpServletRequest request){
		
		StandardError se = StandardError
=======
		List<FieldError> errors = error.getBindingResult().getFieldErrors();
		
		for (FieldError erro : errors) {
			validation.addError(erro.getField(), erro.getDefaultMessage());
		}
		

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validation);
	}

	
	@ExceptionHandler(UsuarioNaoEncontradoException.class)
	public ResponseEntity<StandardError> handler(UsuarioNaoEncontradoException error, HttpServletRequest request) {
			
		StandardError erro = StandardError.builder()
				.timestamp(new Date())
				.message(error.getMessage())
				.path(request.getRequestURI())
				.status(HttpStatus.BAD_REQUEST.value()).build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro); 
		
	}

	@ExceptionHandler(EnderecoNaoEncontradoException.class)
	public ResponseEntity<StandardError> handler(EnderecoNaoEncontradoException error, HttpServletRequest request) {
		
		StandardError erro = StandardError.builder()
				.timestamp(new Date())
				.message(error.getMessage())
				.path(request.getRequestURI())
				.status(HttpStatus.BAD_REQUEST.value()).build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro); 
		
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<StandardError> noSuchElementException(NoSuchElementException error, HttpServletRequest request){
		
		StandardError erro = StandardError
>>>>>>> Stashed changes
				.builder()
				.timestamp(new Date())
				.status(HttpStatus.NOT_FOUND.value())
				.error("Erro ao achar a entidade")
<<<<<<< Updated upstream
				.message(e.getMessage())
				.path(request.getRequestURI())
				.build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(se); 
	}
=======
				.message(error.getMessage())
				.path(request.getRequestURI())
				.build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro); 
	}
	
>>>>>>> Stashed changes
}
