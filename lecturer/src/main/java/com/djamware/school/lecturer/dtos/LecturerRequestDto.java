package com.djamware.school.lecturer.dtos;

public class LecturerRequestDto {
    private String lidn;
    private String name;
    private String email;
    private String phone;
    private String major;

    public String getLidn() {
        return lidn;
    }

    public void setLidn(String lidn) {
        this.lidn = lidn;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
