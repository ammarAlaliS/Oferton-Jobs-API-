package com.AmmarAli.Oferton.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class JobController {
    private final JobService jobService;
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){

        return ResponseEntity.ok(jobService.findAll());
    }
    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        if (job != null){
            jobService.createJob(job);
            return new ResponseEntity<>("Job created successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Unable to create a job: Job object is null", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobsById(@PathVariable Long id){
        Job job = jobService.getJobsById(id);
        if (job != null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
