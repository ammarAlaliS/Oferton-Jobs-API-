package com.AmmarAli.Oferton.company.repositories;

import com.AmmarAli.Oferton.company.entities.Company;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    void createCompany(Company companyEntity);

    Company getCompanyById(Long id);

    boolean updateCompany(Long id, Company updateCompany);

    boolean deleteCompany(Long id);
}
