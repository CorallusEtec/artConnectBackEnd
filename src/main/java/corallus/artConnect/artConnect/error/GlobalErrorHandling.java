package corallus.artConnect.artConnect.error;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import corallus.artConnect.artConnect.error.errors.ArteNotFoundException;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.error.errors.UserAlreadyExistsException;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;

@RestControllerAdvice
public class GlobalErrorHandling {
    
    /**
     * ERRO GENÉRICO QUE RESPONDE À CLASSE RuntimeException
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> genericError(Exception e) {
        ApiError error = new ApiError(
				LocalDateTime.now(),
				HttpStatus.INTERNAL_SERVER_ERROR.name(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				List.of(e.getMessage())
		);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * ARTE NÃO ENCONTRADA
     */
    @ExceptionHandler(ArteNotFoundException.class)
    public ResponseEntity<ApiError> arteNotFound(Exception e) {
        ApiError error = new ApiError(
				LocalDateTime.now(),
				HttpStatus.NOT_FOUND.name(),
				HttpStatus.NOT_FOUND.value(),
				List.of(e.getMessage())
		);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * USUARIO NÃO ENCONTRADO
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> userNotFound(Exception e) {
        ApiError error = new ApiError(
				LocalDateTime.now(),
				HttpStatus.NOT_FOUND.name(),
				HttpStatus.NOT_FOUND.value(),
				List.of(e.getMessage())
		);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * USUARIO JÁ EXISTENTE
     */
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiError> userAlreadyExists(Exception e) {
        ApiError error = new ApiError(
				LocalDateTime.now(),
				HttpStatus.CONFLICT.name(),
				HttpStatus.CONFLICT.value(),
				List.of(e.getMessage())
		);
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    /** 
     * CAMPO INVÁLIDO OU FALTANDO
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> invalidRequest(Exception e) {
        ApiError error = new ApiError(
				LocalDateTime.now(),
				HttpStatus.UNPROCESSABLE_CONTENT.name(),
				HttpStatus.UNPROCESSABLE_CONTENT.value(),
				List.of(e.getMessage())
		);
		return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_CONTENT);
    }

    /** 
     * RECURSO NÃO ENCONTRADO
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> resourceNotFound(Exception e) {
        ApiError error = new ApiError(
				LocalDateTime.now(),
				HttpStatus.NOT_FOUND.name(),
				HttpStatus.NOT_FOUND.value(),
				List.of(e.getMessage())
		);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

  }
