package ie.jak.assignment2.controllers.handlers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(ResourceNotFoundException ex){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleDataIntegrityException(HttpServletResponse httpServletResponse){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse("That would have caused an integrity issue", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiErrorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
     }

     @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> hadnleMissingBody(){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse("Json body was malformed", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
     }
}
