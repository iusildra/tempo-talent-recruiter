package com.tempotalent;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@SpringBootTest
@AutoConfigureGraphQlTester
public abstract class AbstractTest {
  @Autowired
  protected GraphQlTester tester;
  
  @BeforeAll
  static void setupDbCon() {
    System.setProperty("spring.datasource.url", "jdbc:postgresql://localhost:5432/postgres");
  }
}
