package com.djamware.school.study.repositories;

import com.djamware.school.study.model.Student;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentRepository implements PanacheMongoRepository<Student> {

}
