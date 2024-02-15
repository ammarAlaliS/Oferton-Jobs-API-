package com.AmmarAli.Oferton.job.controller;

import com.AmmarAli.Oferton.job.entites.Job;
import com.AmmarAli.Oferton.job.repositories.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @GetMapping
    public ResponseEntity<List<Job>> findAll(){

        return ResponseEntity.ok(jobService.findAll());
    }
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        if (job != null){
            jobService.createJob(job);
            return new ResponseEntity<>("Job created successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Unable to create a job: Job object is null", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{id}")
    public ResponseEntity<Job> getJobsById(@PathVariable Long id){
        Job job = jobService.getJobsById(id);
        if (job != null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteJobsById(@PathVariable Long id){
        boolean deleted = jobService.deleteJobsById(id);
        if (deleted){
            return new ResponseEntity<>("Job delated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
//    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    @PutMapping("{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updateJob){
        boolean updated = jobService.updateJob(id, updateJob);
        if (updated){
            return new ResponseEntity<>("Job has been updated successfully", HttpStatus.OK);
        }
            return new ResponseEntity<>("Jobs could not be update", HttpStatus.BAD_REQUEST);
    }

}
