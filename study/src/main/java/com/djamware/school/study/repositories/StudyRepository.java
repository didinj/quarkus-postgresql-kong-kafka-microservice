package com.djamware.school.study.repositories;

import com.djamware.school.study.model.Study;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudyRepository implements PanacheMongoRepository<Study> {

}
