package com.tempotalent.api.recruiter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RecruiterService {
  private final RecruiterRepository repository;

  public RecruiterService(RecruiterRepository repository) {
    this.repository = repository;
  }

  public Recruiter registerRecruiter(String firstName, String lastName, String phone, String email, String password) {
    Recruiter recruiter = new Recruiter(UUID.randomUUID(), firstName, lastName, phone, email, password);
    return repository.save(recruiter);
  }

  public Recruiter getRecruiter(UUID id) {
    return repository.findById(id).orElseThrow();
  }

  public List<Recruiter> getRecruiterByEmail(String email) {
    var matcher = ExampleMatcher.matching().withMatcher("email", GenericPropertyMatchers.startsWith().ignoreCase());
    var recruiter = new Recruiter();
    recruiter.setEmail(email);

    var example = Example.of(recruiter, matcher);
    return repository.findAll(example);
  }

  public Boolean removeRecruiter(UUID id) {
    repository.deleteById(id);
    return true;
  }

  public Boolean addReviewToCandidate(String title, Integer rating, String comment, UUID candidateID,
      UUID recruiterId) throws IOException, InterruptedException {
    var values = new HashMap<String, Object>();
    values.put("title", title);
    values.put("rating", rating);
    values.put("comment", comment);
    values.put("candidateId", candidateID.toString());
    values.put("recruiterId", recruiterId.toString());
    var reqBody = new ObjectMapper().writeValueAsString(values);
    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder().uri(URI.create("http://localhost:3000/reference"))
        .POST(BodyPublishers.ofString(reqBody)).build();
    var response = client.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(response.body());

    return response.statusCode() == 200;
  }
}
