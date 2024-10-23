package com.djamware.school.study.clients.dtos;

import java.util.Date;

public class StudentResponseDto {
    private Long id;
    private String sidn;
    private String name;
    private String email;
    private String phone;
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
