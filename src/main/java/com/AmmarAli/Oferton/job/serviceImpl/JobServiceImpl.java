package com.AmmarAli.Oferton.job.serviceImpl;

import com.AmmarAli.Oferton.job.entites.Job;
import com.AmmarAli.Oferton.job.repositories.JobRepository;
import com.AmmarAli.Oferton.job.repositories.JobService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    //    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;
    JobRepository jobRepository;
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobRepository.save(job);
    }

    @Override
    public Job getJobsById(Long id) {
        return  jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobsById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updateJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
            if (jobOptional.isPresent()){
                Job job = jobOptional.get();
                job.setName(updateJob.getName());
                job.setDescription(updateJob.getDescription());
                job.setMinSalary(updateJob.getMinSalary());
                job.setMaxSalary(updateJob.getMaxSalary());
                job.setLocation(updateJob.getLocation());
                jobRepository.save(job);
                return true;
            }
        return false;
    }


}
