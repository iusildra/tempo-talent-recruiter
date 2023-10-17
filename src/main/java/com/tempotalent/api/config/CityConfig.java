package com.tempotalent.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.tempotalent.api.controllers.CityController;
import com.tempotalent.api.repositories.CityRepository;
import com.tempotalent.api.services.CityService;

@Configuration
@EnableJpaRepositories("com.tempotalent.api.repositories")
public class CityConfig {
  // @Bean
  // CityController cityController(CityRepository cityRepository) {
  //   return new CityController(cityService(cityRepository));
  // }

  // @Bean
  // CityService cityService(CityRepository repository) {
  //   return new CityService(repository);
  // }
}
