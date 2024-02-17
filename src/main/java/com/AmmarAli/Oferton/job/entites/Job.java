package com.AmmarAli.Oferton.job.entites;

import com.AmmarAli.Oferton.company.entities.Company;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Job-table")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String minSalary;

    private String maxSalary;

    private String location;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
