package com.guisebastiao.api.controllers;

import com.guisebastiao.api.controllers.dtos.DefaultDTO;
import com.guisebastiao.api.controllers.dtos.UserUpdateDTO;
import com.guisebastiao.api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<DefaultDTO> findById(@PathVariable String userId) {
        DefaultDTO response = this.userService.findById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<DefaultDTO> update(@RequestBody @Valid UserUpdateDTO dto) {
        DefaultDTO response = this.userService.update(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<DefaultDTO> delete() {
        DefaultDTO response = this.userService.delete();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
