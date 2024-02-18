package com.AmmarAli.Oferton.job.serviceImpl;

import com.AmmarAli.Oferton.company.entities.Company;
import com.AmmarAli.Oferton.company.repositories.CompanyService;
import com.AmmarAli.Oferton.job.entites.Job;
import com.AmmarAli.Oferton.job.repositories.JobRepository;
import com.AmmarAli.Oferton.job.repositories.JobService;
import com.AmmarAli.Oferton.review.entities.Review;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final CompanyService companyService;

    public JobServiceImpl(JobRepository jobRepository, CompanyService companyService) {
        this.jobRepository = jobRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Job> getAllJob(Long companyId) {
        List<Job> job = jobRepository.findByCompanyId(companyId);
        return job;
    }

    @Override
    public boolean createJob(Long companyId, Job job) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null){
            job.setCompany(company);
            jobRepository.save(job);
            return true;

        }
        return false;
    }

    @Override
    public Job getJobById(Long companyId, Long jobId) {
        List<Job> job = jobRepository.findByCompanyId(companyId);
        return job.stream()
                .filter(review -> review.getId().equals(jobId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateJob(Long companyId, Long jobId, Job job) {
        if (companyService.getCompanyById(companyId) != null){
            job.setCompany(companyService.getCompanyById(companyId));
            job.setId(jobId);
            jobRepository.save(job);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteJob(Long companyId, Long jobId) {
        if (companyService.getCompanyById(companyId) != null
                && jobRepository.existsById(jobId)){
            Job job = jobRepository.findById(jobId).orElse(null);
            assert job != null;
            Company company = job.getCompany();
            company.getReviews().remove(job);
            companyService.updateCompany(companyId, company);
            jobRepository.deleteById(jobId);
            return true;
        }
        return false;
    }

}
