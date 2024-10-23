package com.djamware.school.student.repositories;

import com.djamware.school.student.models.StudyActivity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudyActivityRepository implements PanacheRepository<StudyActivity> {

}
