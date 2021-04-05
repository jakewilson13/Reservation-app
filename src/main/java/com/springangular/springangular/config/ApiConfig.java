package com.springangular.springangular.config;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
//Adding this annotation to an @Configuration class imports the Spring WebFlux configuration from
// WebFluxConfigurationSupport that enables use of annotated controllers and functional endpoints
@EnableWebFlux
@Configuration
public class ApiConfig {

    //bean annotation indicates it's a dependency that it can managed by the spring container
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        //This will allow us to model only the fields we need from a JSON
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        return objectMapper;
        //this objectMapper allows us a serialize a java object into a json String & vice versa
    }
    @Bean
    public ObjectWriter objectWriter(ObjectMapper objectMapper) {
        //Factory method for constructing ObjectWriter that will serialize
        // objects using the default pretty printer for indentation
        return objectMapper.writerWithDefaultPrettyPrinter();
    }

    //now the api is set to serialize and deserialize responses
}
