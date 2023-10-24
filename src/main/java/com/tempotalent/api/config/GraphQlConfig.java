package com.tempotalent.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import graphql.scalars.ExtendedScalars;
import graphql.validation.constraints.standard.ContainerNotEmptyConstraint;
import graphql.validation.constraints.standard.MinConstraint;
import graphql.validation.constraints.standard.PatternConstraint;
import graphql.validation.constraints.standard.PositiveConstraint;
import graphql.validation.constraints.standard.SizeConstraint;
import graphql.validation.rules.OnValidationErrorStrategy;
import graphql.validation.rules.ValidationRules;
import graphql.validation.schemawiring.ValidationSchemaWiring;

@Configuration
public class GraphQlConfig {
    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer() {
        ValidationRules validationRules = ValidationRules.newValidationRules()
                .onValidationErrorStrategy(OnValidationErrorStrategy.RETURN_NULL)
                .addRule(new SizeConstraint())
                .addRule(new MinConstraint())
                .addRule(new PositiveConstraint())
                .addRule(new ContainerNotEmptyConstraint())
                .addRule(new PatternConstraint())
                .build();
        var schemaWiring = new ValidationSchemaWiring(validationRules);

        return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.Date).scalar(ExtendedScalars.GraphQLByte)
                .directiveWiring(schemaWiring).build();
    }
}