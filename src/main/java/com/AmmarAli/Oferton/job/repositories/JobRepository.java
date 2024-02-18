package com.AmmarAli.Oferton.job.repositories;

import com.AmmarAli.Oferton.job.entites.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> getCompanyById(Long companyId);
    List<Job> findByCompanyId(Long companyId);
}
