package ie.jak.assignment2.controllers.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewTenantDTO(@NotEmpty(message = "Tenant's name cannot be empty")
                           @NotBlank(message = "Tenant's name cannot be blank")
                           @NotNull(message = "Tenant's name cannot be null")
                           String tenantName,
                           @NotEmpty(message = "Tenant's email cannot be empty")
                           @NotBlank(message = "Tenant's email cannot be blank")
                           @NotNull(message = "Tenant's email cannot be null")
                           String tenantEmail,
                           @NotEmpty(message = "Tenant's phone number cannot be empty")
                           @NotBlank(message = "Tenant's phone number cannot be blank")
                           @NotNull(message = "Tenant's phone number cannot be null")
                           String tenantPhoneNumber,

                           @Min(value = 0, message = "Invalid property ID")
                           @NotNull(message = "The ID of a property must be provided")
                           Integer propertyId) {
}
