package com.strikes.crudDtoDemo.dto;


import jakarta.validation.constraints.*;

public class CreateStudentRequestDTO {
    @NotBlank(message = "Name cannot be empty")
    @Size(min=2, max = 5, message = "Student name must be within 2 to 50 characters is required")
    private  String name;

    @NotBlank(message = "Email is required")
    @Email (message = "Email is not valid")
    private String email;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age should be minimum 18")
    private int age;

    @NotEmpty(message = "Subject is empty")
    private String subject;

    @NotNull(message = "Roll is not empty")
    private Integer rollNo;

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
