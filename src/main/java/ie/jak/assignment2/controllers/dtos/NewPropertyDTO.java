package ie.jak.assignment2.controllers.dtos;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewPropertyDTO(
        @NotEmpty(message = "Property's address cannot be empty")
        @NotBlank(message = "Property's address cannot be blank")
        @NotNull(message = "Property's address cannot be null")
        String propertyAddress,
        @NotEmpty(message = "Property's eircode cannot be empty")
        @NotBlank(message = "Property's eircode cannot be blank")
        @NotNull(message = "Property's eircode cannot be null")
        String propertyEircode,
        @Min(value = 0, message = "The capacity of a property must be at least 0")
        @NotNull(message = "The capacity of a property must not be null")
        int propertyCapacity,
        @Min(value = 0, message = "The rent of a property must be at least 0")
        @NotNull(message = "The rent of a property must not be null")
        int propertyRentalCost) {
}
