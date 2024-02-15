package com.AmmarAli.Oferton.job;

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
    public Job getJobsById(@PathVariable Long id){
        Job job = jobService.getJobsById(id);
        return  job;
    }

}
