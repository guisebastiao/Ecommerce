package com.guisebastiao.api.services;

import com.guisebastiao.api.controllers.dtos.AuthDTO;
import com.guisebastiao.api.controllers.dtos.DefaultDTO;
import com.guisebastiao.api.exceptions.DuplicateEntityException;
import com.guisebastiao.api.exceptions.EntityNotFoundException;
import com.guisebastiao.api.exceptions.UnauthorizedException;
import com.guisebastiao.api.models.User;
import com.guisebastiao.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public DefaultDTO login(User user) {
        User userFound = this.userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Essa conta não esta cadastrada"));

        if(!passwordEncoder.matches(user.getPassword(), userFound.getPassword())) {
            throw new UnauthorizedException("Senha incorreta");
        }

        AuthDTO authDTO = this.tokenService.generateToken(userFound);

        return new DefaultDTO("Login efetuado com sucesso", Boolean.TRUE, authDTO, null, null);
    }

    public DefaultDTO register(User user) {
        this.userRepository.findByEmail(user.getEmail()).ifPresent(e -> {
            throw new DuplicateEntityException("Essa conta já está cadastrada");
        });

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        this.userRepository.save(user);

        AuthDTO authDTO = this.tokenService.generateToken(user);

        return new DefaultDTO("Sua conta foi cadastrada com sucesso", Boolean.TRUE, authDTO, null, null);
    }
}
