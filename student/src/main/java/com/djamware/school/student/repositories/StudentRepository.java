package com.djamware.school.student.repositories;

import com.djamware.school.student.models.Student;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student> {
    public Student findBySidn(String sidn) {
        return find("sidn = ?1", sidn).firstResult();
    }
}