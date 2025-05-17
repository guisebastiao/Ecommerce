package com.guisebastiao.api.controllers;

import com.guisebastiao.api.controllers.dtos.DefaultDTO;
import com.guisebastiao.api.controllers.dtos.LoginDTO;
import com.guisebastiao.api.controllers.dtos.RegisterDTO;
import com.guisebastiao.api.controllers.mappers.LoginMapper;
import com.guisebastiao.api.controllers.mappers.RegisterMapper;
import com.guisebastiao.api.models.User;
import com.guisebastiao.api.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private RegisterMapper registerMapper;

    @PostMapping("/login")
    public ResponseEntity<DefaultDTO> login(@RequestBody @Valid LoginDTO dto) {
        User user = this.loginMapper.toUser(dto);
        DefaultDTO response = this.authService.login(user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<DefaultDTO> register(@RequestBody @Valid RegisterDTO dto) {
        User user = this.registerMapper.toUser(dto);
        DefaultDTO response = this.authService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
