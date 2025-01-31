package com.example.BuonAppetito.configuration;

import com.example.BuonAppetito.repositories.UtenteRepository;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    @Autowired
    public UtenteRepository utenteRepository;

    @Bean
    public UserDetailsService userDetailsService (){
        return username -> utenteRepository.findUtenteByEmail(username);
    }

    @Bean
    public AuthenticationProvider authenticationProvider () {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder();}

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    private SecurityScheme createAPIKeyScheme () {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

    @Bean
    public OpenAPI openAPI () {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().
                addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes
                        ("Bearer Authenctication", createAPIKeyScheme()));
    }



}
