package ie.jak.assignment2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyUser {

    @Id
    @Column(nullable = false, unique = true)
    private String email;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String role;
    @JsonIgnore
    private boolean locked;

}
