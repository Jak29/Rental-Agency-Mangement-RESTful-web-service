package ie.jak.assignment2;

import ie.jak.assignment2.entities.MyUser;
import ie.jak.assignment2.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private MyUserRepository myUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> myUserOptional = myUserRepository.findByEmail(username);
        if(myUserOptional.isEmpty())
            throw new UsernameNotFoundException(username);
        MyUser myUser = myUserOptional.get();
        return User.withUsername(username)
                .password(myUser.getPassword())
                .accountLocked(false)
                .accountExpired(false)
                .roles(myUser.getRole())
                .disabled(myUser.isLocked())
                .build();
    }
}
