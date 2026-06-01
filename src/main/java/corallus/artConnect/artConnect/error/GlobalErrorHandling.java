package corallus.artConnect.artConnect.error;

import java.time.LocalDateTime;
import java.util.List;

import javax.naming.AuthenticationException;

import corallus.artConnect.artConnect.error.errors.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
	 *
	 * Arte já existente (Já cadastrada)
	 *
	 */

	@ExceptionHandler(ArteAlreadyExistsException.class)
	public ResponseEntity<ApiError> arteAlreadyExists(Exception e) {
		ApiError error = new ApiError(
				LocalDateTime.now(),
				HttpStatus.CONFLICT.name(),
				HttpStatus.CONFLICT.value(),
				List.of(e.getMessage())
		);
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
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

	/**
	 *
	 * Método com argumentos inválidos (Requisição com argumentos inválidos)
	 *
	 */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> methodArgumentNotValid(Exception e) {
        ApiError error = new ApiError(
				LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.name(),
				HttpStatus.BAD_REQUEST.value(),
				List.of("Há campos inválidos na requisição", e.getMessage())
		  );
		  return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> badCredentials(Exception e) {
        ApiError error = new ApiError(
				LocalDateTime.now(),
				HttpStatus.UNAUTHORIZED.name(),
				HttpStatus.UNAUTHORIZED.value(),
				List.of("Credenciais inválidas. Usuário ou senha incorretos", e.getMessage())
		  );
		  return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> authException(Exception e) {
        ApiError error = new ApiError(
				LocalDateTime.now(),
				HttpStatus.UNAUTHORIZED.name(),
				HttpStatus.UNAUTHORIZED.value(),
				List.of("Erro na autenticação", e.getMessage())
		  );
		  return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ApiError> insufficientAuth(Exception e) {
        ApiError error = new ApiError(
				LocalDateTime.now(),
				HttpStatus.UNAUTHORIZED.name(),
				HttpStatus.UNAUTHORIZED.value(),
				List.of("Acesso negado", e.getMessage())
		  );
		  return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
  }
