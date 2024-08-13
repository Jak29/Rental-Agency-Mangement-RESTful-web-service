package ie.jak.assignment2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "sameNamesDifferentProperties", columnNames = {"tenantName", "property_id"}) })
public class Tenant {
    @Id
    @GeneratedValue
    private int tenantId;

    @Column(nullable = false)
    private String tenantName;

    @Column(nullable = false)
    private String tenantEmail;

    @Column(nullable = false)
    private String tenantPhoneNumber;

    @ManyToOne
    @JoinColumn(name="property_id")
    private Property property;

    public Tenant(String tenantName, String tenantEmail, String tenantPhoneNumber, Property property) {
        this.tenantName = tenantName;
        this.tenantEmail = tenantEmail;
        this.tenantPhoneNumber = tenantPhoneNumber;
        this.property = property;
    }

    public Tenant(String tenantName, String tenantEmail, String tenantPhoneNumber, Optional<Property> property) {
    }
}
