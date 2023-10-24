package com.tempotalent.api.companyrecruiter;

import java.util.UUID;

import com.tempotalent.api.company.Company;
import com.tempotalent.api.recruiter.Recruiter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "company_recruiter")
public class CompanyRecruiter {
  @Id
  private UUID id;

  @Column(name = "company_id")
  private Integer companyId;

  @Column(name = "recruiter_id")
  private UUID recruiterId;

  private byte[] proof;

  @ManyToOne
  @JoinColumn(name = "company_id", insertable = false, updatable = false)
  private Company company;

  @ManyToOne
  @JoinColumn(name = "recruiter_id", insertable = false, updatable = false)
  private Recruiter recruiter;

  public CompanyRecruiter() {
  }

  public CompanyRecruiter(UUID id, UUID recruiterId, Integer companyId, byte[] proof) {
    this.id = id;
    this.recruiterId = recruiterId;
    this.companyId = companyId;
    this.proof = proof;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Integer getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Integer companyId) {
    this.companyId = companyId;
  }

  public UUID getRecruiterId() {
    return recruiterId;
  }

  public void setRecruiterId(UUID recruiterId) {
    this.recruiterId = recruiterId;
  }

  public byte[] getProof() {
    return proof;
  }

  public void setProof(byte[] proof) {
    this.proof = proof;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public Recruiter getRecruiter() {
    return recruiter;
  }

  public void setRecruiter(Recruiter recruiter) {
    this.recruiter = recruiter;
  }
}
