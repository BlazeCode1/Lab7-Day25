package org.example.lab7day25.Model;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {
    @NotEmpty(message = "CourseController ID is required")
    private String id;
    @NotEmpty(message = "Title is required")
    @Size(min = 3,max = 100,message = "Title must be 3-100 characters")
    private String title;
    @NotEmpty(message = "Description is required")
    @Size(min = 10,max = 300,message = "Description must be 10-300 characters")
    private String description;

    @NotEmpty(message = "InstructorService ID is required")
    private String instructorId;

    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 100,message = "Capacity must be at most be 100")
    private int capacity;

}
