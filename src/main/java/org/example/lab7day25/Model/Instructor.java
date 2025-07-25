package org.example.lab7day25.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Instructor {
    @NotEmpty(message = "ID is required")
    private String id;

    @NotEmpty(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be 3–50 characters")
    private String name;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email format is invalid")
    private String email;

    @NotEmpty(message = "Specialization is required")
    @Size(min = 2, max = 50, message = "Specialization must be 2–50 characters")
    private String specialization;
}
