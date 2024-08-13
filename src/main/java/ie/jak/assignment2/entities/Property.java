package ie.jak.assignment2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Property {
    @Id
    @GeneratedValue
    private int propertyId;
    @Column(unique = true, nullable = false)
    private String propertyAddress;
    @Column(unique = true, nullable = false)
    private String propertyEircode;
    private int propertyCapacity;
    @Column(nullable = false)
    private int propertyRentalCost;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private List<Tenant> tenants;

    public Property(String propertyAddress, String propertyEircode, int propertyCapacity, int propertyRentalCost) {
        this.propertyAddress = propertyAddress;
        this.propertyEircode = propertyEircode;
        this.propertyCapacity = propertyCapacity;
        this.propertyRentalCost = propertyRentalCost;
    }
}
