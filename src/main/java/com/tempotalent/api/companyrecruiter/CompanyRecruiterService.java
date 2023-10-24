package com.tempotalent.api.companyrecruiter;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class CompanyRecruiterService {
  private CompanyRecruiterRepository repository;

  public CompanyRecruiterService(CompanyRecruiterRepository repository) {
    this.repository = repository;
  }

  public CompanyRecruiter registerCompanyRecruiter(UUID recruiterId, Integer companyId, byte[] proof) {
    var companyRecruiter = new CompanyRecruiter(UUID.randomUUID(), recruiterId, companyId, proof);
    System.out.println("" + companyRecruiter.getId() + " " + companyRecruiter.getCompanyId() + " " + companyRecruiter.getRecruiterId() + " "
        + companyRecruiter.getProof());
    return repository.save(companyRecruiter);
  }

  public Boolean deleteCompanyRecruiter(UUID id) {
    repository.deleteById(id);
    return true;
  }
}
