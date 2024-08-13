package ie.jak.assignment2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.GET, "/properties").permitAll()
                            .requestMatchers(HttpMethod.GET, "/properties/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/tenats/**").hasAnyRole("MANAGER", "STAFF")
                            .requestMatchers(HttpMethod.DELETE, "/tenats/**").hasAnyRole("MANAGER", "STAFF")
                            .requestMatchers(HttpMethod.POST, "/tenats/**").hasAnyRole("MANAGER", "STAFF")
                            .requestMatchers(HttpMethod.PATCH, "/tenats/**").hasAnyRole("MANAGER", "STAFF")
                            .requestMatchers(HttpMethod.DELETE, "/properties/**").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.POST, "/properties/**").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.PATCH, "/properties/**").hasRole("MANAGER")
                            .requestMatchers("/graphql").permitAll()
                            .anyRequest().authenticated();
                })
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }



}
