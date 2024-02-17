package com.AmmarAli.Oferton.review.entities;

import com.AmmarAli.Oferton.company.entities.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reviews-table")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double rating;

    @JsonIgnore
    @ManyToOne
    private Company company;

}
