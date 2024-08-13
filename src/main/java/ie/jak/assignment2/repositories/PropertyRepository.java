package ie.jak.assignment2.repositories;

import ie.jak.assignment2.entities.Property;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
    Optional<Property> findByPropertyAddress(String address);
    List<Property> findAllByOrderByPropertyAddressAsc();
    Optional<Property> findByPropertyAddressIgnoreCase(String name);
    List<Property> findAllByOrderByPropertyCapacityAsc();

    @Modifying
    @Transactional
    @Query("update Property p set p.propertyRentalCost = :rentalCost where p.propertyId = :id")
    int updatePropertyRent(@Param("id") int id, @Param("rentalCost") int rent);

    @Query("select p from Property p left join fetch p.tenants where p.propertyId = :id")
    Optional<Property> findByIdIncludingTenants(@Param("id") int id);

    @Query("select p from Property p left join fetch p.tenants order by p.propertyAddress")
    List<Property> findAllIncludingTenants();
}
