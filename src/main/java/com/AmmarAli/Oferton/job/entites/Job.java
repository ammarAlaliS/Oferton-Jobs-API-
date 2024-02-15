package com.AmmarAli.Oferton.job.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.With;

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

    private  String maxSalary;

    private String location;
}
