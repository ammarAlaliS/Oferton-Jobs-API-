package com.AmmarAli.Oferton.company.entities;

import com.AmmarAli.Oferton.job.entites.Job;
import com.AmmarAli.Oferton.review.entities.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "company-table")
public class Company{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String company_title;
    private String company_description;


    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Review> reviews;

}
