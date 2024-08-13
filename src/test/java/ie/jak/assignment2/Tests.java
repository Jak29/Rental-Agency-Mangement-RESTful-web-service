package ie.jak.assignment2;


import ie.jak.assignment2.entities.Property;
import ie.jak.assignment2.repositories.PropertyRepository;
import ie.jak.assignment2.repositories.TenantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
public class Tests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PropertyRepository propertyRepository;

    @MockBean
    TenantRepository tenantRepository;

    @Test
    void findpropertybyIdendpointifIDexists() throws Exception {
        Property property1 = new Property("10 Cloverfield Lane", "A12B1C1", 10, 1000);
        given(propertyRepository.findById(1)).willReturn(Optional.of(property1));
        mockMvc.perform(get("/properties/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.propertyAddress").value("10 Cloverfield Lane"));
    }

    @Test
    void findpropertybyIdendpointifIDdoesnotexist() throws Exception{
        int id = 100;
        given(propertyRepository.findById(id)).willReturn(Optional.empty());
        mockMvc.perform(get("/properties/{id}", id))
                .andExpect(status().isNotFound());
    }

}
