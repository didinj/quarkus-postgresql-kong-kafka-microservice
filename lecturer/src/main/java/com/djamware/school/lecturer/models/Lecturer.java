package com.djamware.school.lecturer.models;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "lecturer")
public class Lecturer {
    @Id
    @SequenceGenerator(name = "lecturerSequence", sequenceName = "lecturer_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lecturerSequence")
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "lidn", length = 16, nullable = false)
    public String lidn; // Lecturer ID Number

    @Column(name = "name", length = 100, nullable = false)
    public String name;

    @Column(name = "email", length = 100, nullable = false)
    public String email;

    @Column(name = "phone", length = 20, nullable = false)
    public String phone;

    @Column(name = "major", length = 100, nullable = false)
    public String major;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    public Date createdAt;
}
