package com.tempotalent.api.exceptions;

import java.util.List;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class InvalidInput extends RuntimeException implements GraphQLError {

  public InvalidInput(String message) {
    super(message);
  }

  @Override
  public List<SourceLocation> getLocations() {
    return null;
  }

  @Override
  public ErrorClassification getErrorType() {
    return null;
  }
}
