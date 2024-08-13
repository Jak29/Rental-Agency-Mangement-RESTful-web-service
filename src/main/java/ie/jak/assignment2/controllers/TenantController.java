package ie.jak.assignment2.controllers;

import ie.jak.assignment2.controllers.dtos.ChangePropertyDTO;
import ie.jak.assignment2.controllers.dtos.NewTenantDTO;
import ie.jak.assignment2.controllers.handlers.ResourceNotFoundException;
import ie.jak.assignment2.entities.Property;
import ie.jak.assignment2.entities.Tenant;
import ie.jak.assignment2.repositories.PropertyRepository;
import ie.jak.assignment2.repositories.TenantRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tenants")
public class TenantController {
    private final TenantRepository tenantRepository;
    private final PropertyRepository propertyRepository;

    @Autowired
    public TenantController(TenantRepository tenantRepository, PropertyRepository propertyRepository) {
        this.tenantRepository = tenantRepository;
        this.propertyRepository = propertyRepository;
    }

    // GET /tenants/
    // GET /tenants
    @GetMapping({"/", ""})
    List<Tenant> findAll(){
        return tenantRepository.findAll();
    }

    // GET /tenants/{id}
    @GetMapping("/{id}")
    Tenant findById(@PathVariable("id") int tenantId){
        Optional<Tenant> tenantOptional = tenantRepository.findById(tenantId);
        if (tenantOptional.isPresent())
            return tenantOptional.get();
        throw new ResourceNotFoundException("Tenant with ID " + tenantId + " is not present");
    }

    // DELETE /tenants/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable("id") int tenantId) {
        if (tenantRepository.existsById(tenantId))
            tenantRepository.deleteById(tenantId);
        else
            throw new ResourceNotFoundException("Tenant with ID " + tenantId + " is not present");
    }

    // POST /tenants/
    // POST /tenants
    @PostMapping({"/", ""})
    Tenant addTenant(@Valid @RequestBody NewTenantDTO newTenantDTO){
        Optional<Property> propertyOptional = propertyRepository.findById(newTenantDTO.propertyId());
        if (propertyRepository.existsById(newTenantDTO.propertyId()))
            return tenantRepository.save(new Tenant(newTenantDTO.tenantName(), newTenantDTO.tenantEmail(), newTenantDTO.tenantPhoneNumber(), propertyOptional.get()));
        throw new ResourceNotFoundException("Property with ID " + newTenantDTO.propertyId() + " does not exist.");

    }

    // PATCH /tenants/{id}/propertyId
    @PatchMapping("/{id}/propertyId")
    Property updateTenantProperty(@Valid @RequestBody ChangePropertyDTO changePropertyDTO, @PathVariable("id") int tenantId){
        if (tenantRepository.existsById(tenantId)){
            tenantRepository.updateTenantProperty(tenantId, propertyRepository.findById(changePropertyDTO.propertyId()));
            return tenantRepository.findById(tenantId).get().getProperty();
        }
        throw new ResourceNotFoundException("Tenant with Id " + tenantId + " is not present");
    }

}
