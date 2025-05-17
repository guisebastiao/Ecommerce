package com.guisebastiao.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "product_images")
public class ProductImage {

    @Id
    @Column(name = "object_id", nullable = false)
    private UUID objectId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
