package com.AmmarAli.Oferton.company.controller;

import com.AmmarAli.Oferton.company.ServiceImpl.CompanyImplService;
import com.AmmarAli.Oferton.company.entities.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    private final CompanyImplService companyImplService;

    public CompanyController(CompanyImplService companyImplService) {
        this.companyImplService = companyImplService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        try {
            List<Company> companies = companyImplService.getAllCompanies();
            return ResponseEntity.ok(companies);
        } catch (Exception e) {
            logger.error("Error fetching all companies", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable Long id) {
        try {
            Company company = companyImplService.getCompanyById(id);
            if (company != null) {
                return new ResponseEntity<>(company, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Unable to find Company related with this ID", HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            logger.error("Invalid company ID: " + id, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Error fetching company with ID: " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        try {
            if (company != null) {
                companyImplService.createCompany(company);
                return new ResponseEntity<>("Company has been created successfully", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Unable to create the Company",HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Error creating company", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company updateCompany) {
        try {
            boolean updated = companyImplService.updateCompany(id, updateCompany);
            if (updated) {
                return new ResponseEntity<>("Company has been updated successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Company does not exist whit this ID", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error updating company with ID: " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        try {
            boolean deleted = companyImplService.deleteCompany(id);
            if (deleted) {
                return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Company does not exit whit this ID",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error deleting company with ID: " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}