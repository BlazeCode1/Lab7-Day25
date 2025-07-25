package org.example.lab7day25.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.lab7day25.Api.ApiResponse;
import org.example.lab7day25.Model.Course;
import org.example.lab7day25.Model.Student;
import org.example.lab7day25.Service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService service;


    @GetMapping("/get")
    public ResponseEntity<?> getStudents(){
        if(service.getStudents().isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponse("Students List Is Empty"));
        }
        return ResponseEntity.ok(service.getStudents());
    }


    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student student, Errors err){
        if(err.hasErrors()){
            return ResponseEntity.badRequest().body(new ApiResponse(err.getFieldError().getDefaultMessage()));
        }
        if(service.addStudent(student)){
            return ResponseEntity.ok(new ApiResponse("Student Added Successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Student Already Added"));
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@Valid @RequestBody Student student,Errors err) {
        if (err.hasErrors()) {
            return ResponseEntity.badRequest().body(new ApiResponse(err.getFieldError().getDefaultMessage()));
        }

        if (service.updateStudent(student)) {
            return ResponseEntity.ok(new ApiResponse("Student Updated Successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Student ID not Found"));

    }

    @DeleteMapping("/delete/{ID}")
    public ResponseEntity<?> deleteStudent(@PathVariable String ID){
        if(service.deleteStudent(ID)){
            return ResponseEntity.ok(new ApiResponse("Student Deleted Successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Student ID not found"));
    }



    @GetMapping("/get/courses/{ID}")
    public ResponseEntity<?> getStudentCourses(@PathVariable String ID){
        if(service.getStudentCourses(ID) == null){
            return ResponseEntity.badRequest().body(new ApiResponse("There Are No Students.."));
        }

        if(service.getStudentCourses(ID).isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponse("Student has no courses"));
        }

        return ResponseEntity.ok(service.getStudentCourses(ID));

    }


    @PostMapping("/enroll/{courseID}/{studentID}")
    public ResponseEntity<?> enrollToCourse(@PathVariable String courseID,@PathVariable String studentID){
        int result = service.enrollCourse(courseID,studentID);

        return switch (result) {
            case 0 -> ResponseEntity.badRequest().body(new ApiResponse("There Are No Courses"));
            case 1 -> ResponseEntity.badRequest().body(new ApiResponse("There Are No Students"));
            case 2 -> ResponseEntity.badRequest().body(new ApiResponse("Course Is At Full capacity"));
            case 4 -> ResponseEntity.badRequest().body(new ApiResponse("Course ID Not found"));
            case 5 -> ResponseEntity.badRequest().body(new ApiResponse("Student Not Found"));
            case 6 -> ResponseEntity.badRequest().body(new ApiResponse("Student Already Enrolled to Course"));
            default -> ResponseEntity.ok(new ApiResponse("added Course to student."));
        };

    }


    @GetMapping("/get/major/{major}")
    public ResponseEntity<?> getStudentsByMajor(@PathVariable String major){
        if(service.getStudentsByMajor(major).isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponse("No Students Found"));
        }
        return ResponseEntity.ok(service.getStudentsByMajor(major));

    }

    @GetMapping("/get/gpa/{GPA}")
    public ResponseEntity<?> getStudentsByGPA(@PathVariable double GPA){
        if(service.getStudentsByGPA(GPA).isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponse("No Students With this GPA"));
        }
        return ResponseEntity.ok(service.getStudentsByGPA(GPA));
    }







}
