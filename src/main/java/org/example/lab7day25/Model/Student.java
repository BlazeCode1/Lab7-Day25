package org.example.lab7day25.Model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Student {

    @NotEmpty(message = "Student ID is required")
    private String id;
    @NotEmpty(message = "Student Name is required")
    @Size(min = 4,max = 20,message = "Student Name must be 4-20 characters")
    private String name;

    @NotEmpty(message = "Student Email is required")
    @Email(message = "Email format is invalid")
    private String email;

    @NotEmpty(message = "Student Major is required")
    @Size(min = 2,max = 50,message = "Major must be 2-50 characters")
    private String major;

    @NotNull(message = "GPA Cannot Be Empty")
    private double GPA;

    private ArrayList<Course> enrolledCourses;

}
