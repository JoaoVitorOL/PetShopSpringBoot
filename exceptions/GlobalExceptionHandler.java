package com.example.demo.exceptions;

import com.example.demo.exceptions.BusinessRuleException;
import com.example.demo.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;
import jakarta.persistence.EntityNotFoundException;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


// Intercepta exceções lançadas pelos serviços e controladores
 // e converte cada uma em uma resposta HTTP adequada.

@ControllerAdvice
public class GlobalExceptionHandler {

    // Trata EntityNotFoundException (JPA) -> 404
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleJpaEntityNotFound(EntityNotFoundException ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("error", "Recurso não encontrado");
        response.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    //  Trata ObjectNotFoundException -> 404
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Object> handleObjectNotFound(ObjectNotFoundException ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("error", "Recurso não encontrado");
        response.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    //  Trata erros de validação gerados por DTOs com @Valid.
    //  Retorna 400 e uma lista de campos com seus respectivos erros.

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Erro de validação nos campos");

        // Lista de erros campo -> mensagem
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }

        response.put("fields", fieldErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    //  Trata conflitos de integridade, como valores únicos duplicados.
    //  Ex.: CPF duplicado, email duplicado ou referência inválida.
    //  Retorna 409 Conflict.

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.CONFLICT.value());
        response.put("error", "Violação de integridade de dados");

        String message = ex.getMostSpecificCause().getMessage();

        if (message != null && message.contains("cpf")) {
            response.put("message", "CPF já cadastrado no sistema.");
        } else if (message != null && message.contains("email")) {
            response.put("message", "E-mail já cadastrado no sistema.");
        } else if (message != null && message.contains("telefone")) {
            response.put("message", "Telefone já cadastrado no sistema.");
        } else {
            response.put("message", "Registro duplicado ou referência inválida.");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }


    //  Trata BusinessRuleException -> 409 Conflict.
    //  Usado quando a regra de negócio impede a operação,
    // mesmo sem erro técnico.

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<Object> handleBusinessRuleException(BusinessRuleException ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.CONFLICT.value());
        response.put("error", "Violação de regra de negócio");
        response.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }


    //  Trata IllegalArgumentException -> 400 Bad Request.
    //  Geralmente indica argumento inválido enviado ao serviço.

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Argumento inválido");
        response.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Captura qualquer outra exceção não tratada.
    //  Retorna 500 (Erro interno).

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", "Erro interno no servidor");
        response.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
