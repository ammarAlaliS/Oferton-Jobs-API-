package com.AmmarAli.Oferton.job.repositories;


import com.AmmarAli.Oferton.job.entites.Job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);
    Job getJobsById(Long id);
    boolean deleteJobsById(Long id);
    boolean updateJob(Long id, Job updateJob);
}
