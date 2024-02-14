package com.AmmarAli.Oferton.job;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public class JobController {
    public List<Job> findAll(){
        return jobs;
    }

}
