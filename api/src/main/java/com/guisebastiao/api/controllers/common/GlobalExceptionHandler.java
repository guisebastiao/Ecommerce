package com.guisebastiao.api.controllers.common;

import com.guisebastiao.api.controllers.dtos.DefaultDTO;
import com.guisebastiao.api.controllers.dtos.FieldErrorDTO;
import com.guisebastiao.api.exceptions.BadRequestException;
import com.guisebastiao.api.exceptions.DuplicateEntityException;
import com.guisebastiao.api.exceptions.EntityNotFoundException;
import com.guisebastiao.api.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();

        List<FieldErrorDTO> fieldErrorDTOs = fieldErrors.stream()
                .map(fe -> new FieldErrorDTO(fe.getField(), fe.getDefaultMessage())
        ).toList();

        DefaultDTO response = new DefaultDTO("Erro de validação", Boolean.FALSE, null, null, fieldErrorDTOs);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<DefaultDTO> handleDuplicateEntityException(DuplicateEntityException e) {
        DefaultDTO response = new DefaultDTO(e.getMessage(), Boolean.FALSE, null, null, null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<DefaultDTO> handleEntityNotFoundException(EntityNotFoundException e) {
        DefaultDTO response = new DefaultDTO(e.getMessage(), Boolean.FALSE, null, null, null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<DefaultDTO> handleBadRequestException(BadRequestException e) {
        DefaultDTO response = new DefaultDTO(e.getMessage(), Boolean.FALSE, null, null, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<DefaultDTO> handleUnauthorizedException(Exception e) {
        DefaultDTO response = new DefaultDTO(e.getMessage(), Boolean.FALSE, null, null, null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<DefaultDTO> handleNotFound(NoHandlerFoundException e) {
        DefaultDTO response = new DefaultDTO("Rota não encontrada", Boolean.FALSE, null, null, null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<DefaultDTO> handleRuntimeException(RuntimeException e) {
        DefaultDTO response = new DefaultDTO("Ocorreu um erro inesperado, tente novamente mais tarde", Boolean.FALSE, null, null, null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
