package com.guisebastiao.api.controllers;

import com.guisebastiao.api.controllers.dtos.DefaultDTO;
import com.guisebastiao.api.controllers.dtos.UploadProfilePictureDTO;
import com.guisebastiao.api.services.ProfilePictureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/profile-picture")
public class ProfilePictureController {

    @Autowired
    private ProfilePictureService profilePictureService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DefaultDTO> uploadProfilePicture(@ModelAttribute @Valid UploadProfilePictureDTO picture) throws Exception {
        DefaultDTO response = this.profilePictureService.uploadProfilePicture(picture.picture());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DefaultDTO> findProfilePicture(@PathVariable String userId) throws Exception {
        DefaultDTO response = this.profilePictureService.findProfilePicture(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<DefaultDTO> deleteProfilePicture() throws Exception {
        DefaultDTO response = this.profilePictureService.deleteProfilePicture();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
