package ie.jak.assignment2.controllers;

import ie.jak.assignment2.controllers.dtos.NewPropertyDTO;
import ie.jak.assignment2.controllers.dtos.NewRentDTO;
import ie.jak.assignment2.controllers.handlers.ResourceNotFoundException;
import ie.jak.assignment2.entities.Property;
import ie.jak.assignment2.entities.Tenant;
import ie.jak.assignment2.repositories.PropertyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/properties")
public class PropertyController {
    @Autowired private PropertyRepository propertyRepository;

    // GET /properties/
    // GET /properties
    @GetMapping({"/", ""})
    List<Property> findAll(){
        return propertyRepository.findAll();
    }

    // GET /properties/{id}
    @GetMapping("/{id}")
    Property findById(@PathVariable("id") int propertyId){
        Optional<Property> propertyOptional = propertyRepository.findById(propertyId);
        if (propertyOptional.isPresent())
            return propertyOptional.get();
        throw new ResourceNotFoundException("Property with ID " + propertyId + " is not present");
    }

    // DELETE /properties/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable("id") int propertyId){
        if(propertyRepository.existsById(propertyId))
            propertyRepository.deleteById(propertyId);
        else
            throw new ResourceNotFoundException("Property with ID " + propertyId + " is not present");
    }

    // POST /properties/
    // POST /properties
    @PostMapping({"/", ""})
    Property addNewProperty(@Valid @RequestBody NewPropertyDTO newPropertyDTO){
        return propertyRepository.save(new Property(newPropertyDTO.propertyAddress(), newPropertyDTO.propertyEircode(), newPropertyDTO.propertyCapacity(), newPropertyDTO.propertyRentalCost()));
    }

    // PATCH /properties/{id}/rent
    @PatchMapping("/{id}/rent")
    Property updateRent(@Valid @RequestBody NewRentDTO newRentDTO, @PathVariable("id") int propertyId){
        if (propertyRepository.existsById(propertyId)){
            propertyRepository.updatePropertyRent(propertyId, newRentDTO.newRent());
            return propertyRepository.findById(propertyId).get();
        }
        throw new ResourceNotFoundException("Property with Id " + propertyId + " is not present");
    }
}
