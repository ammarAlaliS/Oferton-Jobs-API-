package com.AmmarAli.Oferton.company.repositories;

import com.AmmarAli.Oferton.company.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
