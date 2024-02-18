package com.AmmarAli.Oferton.job.repositories;


import com.AmmarAli.Oferton.job.entites.Job;
import com.AmmarAli.Oferton.review.entities.Review;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JobService {

    List<Job> getAllJob(Long companyId);
    boolean createJob(Long companyId, Job job);

    Job getJobById(Long companyId, Long jobId);

    boolean updateJob(Long companyId, Long jobId, Job job);

    boolean deleteJob(Long companyId, Long jobId);
}
