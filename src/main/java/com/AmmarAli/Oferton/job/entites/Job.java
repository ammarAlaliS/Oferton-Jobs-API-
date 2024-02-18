package com.AmmarAli.Oferton.job.entites;

import com.AmmarAli.Oferton.company.entities.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Job-table")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String job_title;

    private String job_description;

    private String job_minSalary;

    private String job_maxSalary;

    private String job_location;

    @JsonIgnore
    @ManyToOne
    private Company company;
}
