package com.guisebastiao.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "profile_pictures")
public class ProfilePicture {

    @Id
    @Column(name = "object_id")
    private UUID objectId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
