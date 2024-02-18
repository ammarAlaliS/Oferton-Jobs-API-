package com.AmmarAli.Oferton.job.controller;

import com.AmmarAli.Oferton.job.entites.Job;
import com.AmmarAli.Oferton.job.repositories.JobService;
import com.AmmarAli.Oferton.review.entities.Review;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/company/{companyId}")
public class JobController {

    final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @GetMapping("/job")
    public ResponseEntity<?> getAllJob(@PathVariable Long companyId){
        List<Job> jobs = jobService.getAllJob(companyId);
        if (jobs != null && !jobs.isEmpty()) {
            return ResponseEntity.ok(jobs);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No jobs found for companyId: " + companyId);
        }
    }
    @GetMapping("/job/{jobId}")
    public ResponseEntity<?> getJobById(@PathVariable Long companyId,
                          @PathVariable Long jobId){
        Job isJobExits = jobService.getJobById(companyId, jobId);
        if (isJobExits != null){
            return new ResponseEntity<>(jobService.getJobById(companyId, jobId), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No jobs found with jobId: " + jobId + " for companyId: " + companyId);

    }
    @PostMapping("/job")
    public ResponseEntity<String> createJob(@PathVariable Long companyId,
                                            @RequestBody Job job){
        boolean isJobCreated = jobService.createJob(companyId, job);
        if (isJobCreated){
            return new ResponseEntity<>("Job created successfully", HttpStatus.CREATED);
        }
            return new ResponseEntity<>("Unable to create a Job", HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/job/{jobId}")
    public ResponseEntity<String>  updateJob(@PathVariable Long companyId,
                                                @PathVariable Long jobId,
                                                @RequestBody Job job){
        boolean isJobUpdated = jobService.updateJob(companyId,jobId , job);
        if (isJobUpdated){
            return new ResponseEntity<>("job updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("job does not exits", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/job/{jobId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long jobId){
        boolean isJobDeleted = jobService.deleteJob(companyId,jobId);
        if (isJobDeleted){
            return new ResponseEntity<>("job Deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("job does not exits", HttpStatus.NOT_FOUND);
    }

}