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
    private String review_user_name;
    private String review_description;
    private double review_rating;

    @JsonIgnore
    @ManyToOne
    private Company company;

}
