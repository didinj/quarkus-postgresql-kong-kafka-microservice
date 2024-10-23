package com.djamware.school.student.dtos;

public class StudentRequestDto {
    private String sidn;
    private String name;
    private String email;
    private String phone;

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
}
