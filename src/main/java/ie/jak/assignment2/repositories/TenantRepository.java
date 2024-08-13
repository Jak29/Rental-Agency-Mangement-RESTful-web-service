package ie.jak.assignment2.repositories;

import ie.jak.assignment2.entities.Property;
import ie.jak.assignment2.entities.Tenant;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant, Integer> {
    @Modifying
    @Transactional
    @Query("update Tenant t set t.property = :property where t.tenantId = :id")
    int updateTenantProperty(@Param("id") int id, @Param("property") Optional<Property> property);
}
