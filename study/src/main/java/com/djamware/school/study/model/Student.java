package com.djamware.school.study.model;

import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "student")
public class Student {
    private String sidn;
    private String name;

    public String getSidn() {
        return sidn;
    }

    public void setSidn(String sidn) {
        this.sidn = sidn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
