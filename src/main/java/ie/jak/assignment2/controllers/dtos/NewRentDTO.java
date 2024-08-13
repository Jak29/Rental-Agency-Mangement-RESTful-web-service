package ie.jak.assignment2.controllers.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record NewRentDTO(
        @Min(value = 0, message = "The rent of a property must be at least 0")
        @NotNull(message = "The rent of a property must not be null")
        Integer newRent) {
}
