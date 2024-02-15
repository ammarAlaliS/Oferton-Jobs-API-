package com.AmmarAli.Oferton.job;


import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);
    Job getJobsById(Long id);
}
