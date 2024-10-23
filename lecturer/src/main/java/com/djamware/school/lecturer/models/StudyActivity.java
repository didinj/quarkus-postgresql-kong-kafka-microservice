package com.djamware.school.lecturer.models;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "study_activity")
public class StudyActivity {
    @Id
    @SequenceGenerator(name = "studySequence", sequenceName = "study_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studySequence")
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "sidn", length = 16, nullable = false)
    public List<String> sidn;

    @Column(name = "lidn", length = 16, nullable = false)
    public String lidn;

    @Column(name = "study_name", length = 100, nullable = false)
    public String studyName;

    @Column(name = "study_start_time", nullable = false)
    public Date studyStartTime;

    @Column(name = "study_end_time", nullable = false)
    public Date studyEndTime;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    public Date createdAt;
}
