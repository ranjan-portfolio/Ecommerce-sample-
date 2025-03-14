package com.product.ecommerce.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;
    @OneToOne(mappedBy = "product")
    private Product product;
    private String ratingDescription;
    private Float  ratingValue;
    @OneToOne(mappedBy = "users")
    private String username;
    private Timestamp ratingDate;
}
