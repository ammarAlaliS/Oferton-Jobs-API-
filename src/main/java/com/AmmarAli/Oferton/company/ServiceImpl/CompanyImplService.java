package com.AmmarAli.Oferton.company.ServiceImpl;

import com.AmmarAli.Oferton.company.entities.Company;
import com.AmmarAli.Oferton.company.repositories.CompanyRepository;
import com.AmmarAli.Oferton.company.repositories.CompanyService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyImplService implements CompanyService {
    private final CompanyRepository companyRepository;
    public CompanyImplService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @Override
    public List<Company> getAllCompanies() {
       return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        if (company != null){
            companyRepository.save(company);
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateCompany(Long id, Company updateCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()){
            Company company = companyOptional.get();
            company.setName(updateCompany.getName());
            company.setDescription(updateCompany.getDescription());
            company.setJobs(updateCompany.getJobs());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    public boolean deleteCompany(Long id) {
        try {
            Optional<Company> companyOptional = companyRepository.findById(id);
            if (companyOptional.isPresent()) {
                companyRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }



}
