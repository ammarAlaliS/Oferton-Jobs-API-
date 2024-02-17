package com.AmmarAli.Oferton.job.controller;

import com.AmmarAli.Oferton.job.entites.Job;
import com.AmmarAli.Oferton.job.repositories.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<Job> jobs = jobService.findAll();
            return ResponseEntity.ok(jobs);
        } catch (Exception e) {
            logger.error("Error fetching all jobs", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to find all Job");
        }
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        try {
            if (job != null) {
                jobService.createJob(job);
                return new ResponseEntity<>("Job created successfully", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Unable to create a job: Job object is null", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Error creating job", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        try {
            Job job = jobService.getJobsById(id);
            if (job != null) {
                return new ResponseEntity<>(job, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error fetching job with ID: " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        try {
            boolean deleted = jobService.deleteJobsById(id);
            if (deleted) {
                return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Unable to delete Job Id no found",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error deleting job with ID: " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updateJob) {
        try {
            boolean updated = jobService.updateJob(id, updateJob);
            if (updated) {
                return new ResponseEntity<>("Job has been updated successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Job does not exist", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error updating job with ID: " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}