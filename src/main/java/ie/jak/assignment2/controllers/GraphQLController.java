package ie.jak.assignment2.controllers;

import ie.jak.assignment2.entities.Property;
import ie.jak.assignment2.entities.Tenant;
import ie.jak.assignment2.repositories.PropertyRepository;
import ie.jak.assignment2.repositories.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphQLController {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private TenantRepository tenantRepository;

    @QueryMapping(value = "properties")
    List<Property> getAllProperties(){
        return propertyRepository.findAll();
    }

    @QueryMapping
    Property findProperty(@Argument int id){
        return propertyRepository.findById(id).orElse(null);
    }

    @MutationMapping
    @Secured(value = "ROLE_MANAGER")
    Property createProperty(@Argument("address") String propertyAddress, @Argument("eircode") String propertyEircode, @Argument("capacity") int propertyCapacity, @Argument("rent") int propertyRentalCost ){
        return propertyRepository.save(new Property(propertyAddress, propertyEircode, propertyCapacity, propertyRentalCost));
    }

    @MutationMapping
    @Secured(value = {"ROLE_MANAGER", "ROLE_STAFF"})
    Tenant createTenant(@Argument("name") String tenantName, @Argument("email") String tenantEmail, @Argument("phoneNumber") String tenantPhoneNumber, @Argument("property") int propertyId ){
        Optional<Property> property = propertyRepository.findById(propertyId);
        return tenantRepository.save(new Tenant(tenantName, tenantEmail, tenantPhoneNumber, property));
    }

    @MutationMapping
    @Secured(value = "ROLE_MANAGER")
    boolean deleteProperty(@Argument int id){
        if (propertyRepository.existsById(id)) {
            propertyRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
