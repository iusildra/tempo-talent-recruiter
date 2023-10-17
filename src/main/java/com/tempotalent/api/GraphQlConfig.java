package com.tempotalent.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.GraphQlSource;

import graphql.GraphQL;

@Configuration(proxyBeanMethods = false)
public class GraphQlConfig {

	private GraphQlConfig() {
	}

	public static GraphQL graphQL() {
		return GraphQlSource.schemaResourceBuilder().build().graphQl();
	}
}