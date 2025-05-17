package com.guisebastiao.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}
