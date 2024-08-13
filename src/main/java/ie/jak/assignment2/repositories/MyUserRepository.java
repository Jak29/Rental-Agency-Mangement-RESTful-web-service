package ie.jak.assignment2.repositories;

import ie.jak.assignment2.entities.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, String> {
    Optional<MyUser> findByEmail(String username);
}
