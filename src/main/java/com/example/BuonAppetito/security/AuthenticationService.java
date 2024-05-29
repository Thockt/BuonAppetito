package com.example.BuonAppetito.security;

import com.example.BuonAppetito.entities.TokenBlackList;
import com.example.BuonAppetito.entities.Utente;
import com.example.BuonAppetito.enums.Role;
import com.example.BuonAppetito.exceptions.ComuneNotFoundException;
import com.example.BuonAppetito.repositories.UtenteRepository;
import com.example.BuonAppetito.requests.AuthenticationRequest;
import com.example.BuonAppetito.requests.RegistrationRequest;
import com.example.BuonAppetito.responses.AuthenticationResponse;
import com.example.BuonAppetito.services.ComuneService;
import com.example.BuonAppetito.services.TokenBlackListService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AuthenticationService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private ComuneService comuneService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenBlackListService tokenBlackListService;


    public AuthenticationResponse register (RegistrationRequest request) throws ComuneNotFoundException {
        var user = Utente.builder()
                .nome(request.getNome())
                .cognome(request.getCognome())
                .indirizzo(request.getIndirizzo())
                .email(request.getEmail())
                .telefono(request.getTelefono())
                .dataNascita(request.getDataNascita())
                .città(request.getCittà())
                .role(Role.UTENTE)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        var jwtToken = jwtService.generateToken(user);
        user.setRegistrationToken(jwtToken);
        utenteRepository.saveAndFlush(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate (AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword()
        ));
        var user = utenteRepository.findUtenteByEmail(authenticationRequest.getEmail());
        var jwtToken = jwtService.generateToken(user);
        if(tokenBlackListService.tokenNotValidFromUtenteById(user.getId()).contains(jwtToken)) {
            String email = jwtService.extractUsername(jwtToken);
            UserDetails userDetails = utenteRepository.findUtenteByEmail(email);

            String newToken = jwtService.generateToken(userDetails);
            return AuthenticationResponse.builder().token(newToken).build();
        }
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public void logout (HttpServletRequest httpRequest, Long id) {
        String token = extractTokenFromRequest(httpRequest);
        TokenBlackList tokenBlackList = TokenBlackList.builder()
                .utente(utenteRepository.getReferenceById(id))
                .token(token)
                .build();
        int x =0;
        tokenBlackListService.createTokenBlackList(tokenBlackList);
    }

    public String extractTokenFromRequest (HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if(StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        int x=0;
        return null;
    }



}
