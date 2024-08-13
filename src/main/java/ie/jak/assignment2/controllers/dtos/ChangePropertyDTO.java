package ie.jak.assignment2.controllers.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ChangePropertyDTO(@Min(value = 0, message = "Invalid property ID")
                             @NotNull(message = "The ID of a property must be provided")
                             Integer propertyId) {

}
