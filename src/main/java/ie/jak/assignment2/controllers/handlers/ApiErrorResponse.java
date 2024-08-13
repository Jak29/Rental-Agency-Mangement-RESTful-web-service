package ie.jak.assignment2.controllers.handlers;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ApiErrorResponse(String message, @JsonFormat(shape =JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm" ) LocalDateTime localDateTime) {
}
