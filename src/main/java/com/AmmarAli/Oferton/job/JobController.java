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
    public List<Job> findAll(){
        return jobService.findAll();
    }
    @PostMapping("/jobs")
    public String createJob(@RequestBody Job job){
        jobService.createJob(job);
        return "Job added successfully";
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
