package com.djamware.school.lecturer.repositories;

import com.djamware.school.lecturer.models.Lecturer;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LecturerRepository implements PanacheRepository<Lecturer> {
    public Lecturer findByLidn(String lidn) {
        return find("lidn = ?1", lidn).firstResult();
    }
}
