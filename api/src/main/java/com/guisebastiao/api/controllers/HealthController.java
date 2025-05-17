package com.guisebastiao.api.controllers;

import com.guisebastiao.api.controllers.dtos.DefaultDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthController {

    @GetMapping
    public ResponseEntity<DefaultDTO> health() {
        DefaultDTO response = new DefaultDTO("API está rodando", Boolean.TRUE, null, null, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
