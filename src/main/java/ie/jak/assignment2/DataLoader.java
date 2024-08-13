package ie.jak.assignment2;

import ie.jak.assignment2.entities.MyUser;
import ie.jak.assignment2.entities.Property;
import ie.jak.assignment2.entities.Tenant;
import ie.jak.assignment2.repositories.MyUserRepository;
import ie.jak.assignment2.repositories.PropertyRepository;
import ie.jak.assignment2.repositories.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    private final PropertyRepository propertyRepository;
    private final TenantRepository tenantRepository;
    private final MyUserRepository myUserRepository;

    @Autowired
    public DataLoader(PropertyRepository propertyRepository, TenantRepository tenantRepository, MyUserRepository myUserRepository) {
        this.propertyRepository = propertyRepository;
        this.tenantRepository = tenantRepository;
        this.myUserRepository = myUserRepository;
    }



    @Override
    public void run(String... args) throws Exception {
        Property property1 = propertyRepository.save(new Property("10 Cloverfield Lane", "A12B1C1", 10, 1000));
        Property property2 = propertyRepository.save(new Property("11 Cloverfield Lane", "A12B1D2", 2, 1200));
        Property property3 = propertyRepository.save(new Property("12 Cloverfield Lane", "A12B1E3", 3, 900));
        Property property4 = propertyRepository.save(new Property("13 Cloverfield Lane", "A12B1F4", 5, 2000));
        Property property5 = propertyRepository.save(new Property("14 Cloverfield Lane", "A12B1G5", 6, 1000));

        Tenant tentant1 = tenantRepository.save(new Tenant("Jak", "jak@gmail.com", "0831234567", property1));
        Tenant tentant2 = tenantRepository.save(new Tenant("Kevin", "kevin@gmail.com", "72282854", property1));
        Tenant tentant3 = tenantRepository.save(new Tenant("Max", "max@gmail.com", "7245724", property1));
        Tenant tentant4 = tenantRepository.save(new Tenant("Jill", "jill@gmail.com", "3452752", property1));

        Tenant tentant5 = tenantRepository.save(new Tenant("Nicole", "nicole@gmail.com", "5345345345", property2));
        Tenant tentant6 = tenantRepository.save(new Tenant("Lisa", "lisa@gmail.com", "6421346", property2));

        Tenant tentant7 = tenantRepository.save(new Tenant("Aaron", "aaron@gmail.com", "613476147", property3));
        Tenant tentant8 = tenantRepository.save(new Tenant("Anne", "anne@gmail.com", "7542321", property3));
        Tenant tentant9 = tenantRepository.save(new Tenant("Charlie", "charlie@gmail.com", "13475433", property3));

        Tenant tentant10 = tenantRepository.save(new Tenant("Liam", "liam@gmail.com", "12477522", property4));
        Tenant tentant11 = tenantRepository.save(new Tenant("Abby", "abby@gmail.com", "762134224", property4));
        Tenant tentant12 = tenantRepository.save(new Tenant("Ella", "ella@gmail.com", "246724572", property4));
        Tenant tentant13 = tenantRepository.save(new Tenant("August", "august@gmail.com", "134657422", property4));
        Tenant tentant14 = tenantRepository.save(new Tenant("Jude", "jude@gmail.com", "243578245", property4));

        Tenant tentant15 = tenantRepository.save(new Tenant("Wayne", "wayne@gmail.com", "86554262", property5));
        Tenant tentant16 = tenantRepository.save(new Tenant("Courtney", "courtney@gmail.com", "863546345", property5));
        Tenant tentant17 = tenantRepository.save(new Tenant("Logan", "logan@gmail.com", "5662456245", property5));
        Tenant tentant18 = tenantRepository.save(new Tenant("Katie", "katie@gmail.com", "254624576", property5));
        Tenant tentant19 = tenantRepository.save(new Tenant("Jen", "jen@gmail.com", "52651214136", property5));
        Tenant tentant20 = tenantRepository.save(new Tenant("Alex", "alex@gmail.com", "146363465", property5));

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        myUserRepository.save(new MyUser("email1@gmail.com", passwordEncoder.encode("secret"), "MANAGER", false));
        myUserRepository.save(new MyUser("email2@gmail.com", passwordEncoder.encode("secret"), "STAFF", false));
        myUserRepository.save(new MyUser("email3@gmail.com", passwordEncoder.encode("secret"), "MANAGER", true));

    }
}
