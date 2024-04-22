package com.anish.email.Idvalidation.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository) {
        return args -> {
            Student nithil = new Student(
                    "Nithil",
                    "nithilanish@gmail.com",
                    LocalDate.of(2008, Month.SEPTEMBER, 25)
            );
            Student mohit = new Student(
                    "Mohit",
                    "mohitanish@gmail.com",
                    LocalDate.of(2013, Month.DECEMBER, 24)
            );
            repository.saveAll(
                    List.of(nithil, mohit)
            );
        };
    }

}
