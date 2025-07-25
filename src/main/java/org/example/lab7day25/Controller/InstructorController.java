package org.example.lab7day25.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.lab7day25.Api.ApiResponse;
import org.example.lab7day25.Model.Instructor;
import org.example.lab7day25.Service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/instructor")
@RequiredArgsConstructor
public class InstructorController {


    private final InstructorService service;


    @GetMapping("/get")
    public ResponseEntity<?> getInstructors(){
        if(service.getInstructors().isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponse("Instructor List Is Empty"));
        }
        return ResponseEntity.ok(service.getInstructors());
    }


    @PostMapping("/add")
    public ResponseEntity<?> addInstructor(@Valid @RequestBody Instructor instructor, Errors err){
        if(err.hasErrors()){
            return ResponseEntity.badRequest().body(new ApiResponse(err.getFieldError().getDefaultMessage()));
        }
        if(service.addInstructor(instructor)){
            return ResponseEntity.ok(new ApiResponse("Instructor Added Successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Instructor Already Added"));
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateInstructor(@Valid @RequestBody Instructor instructor,Errors err) {
        if (err.hasErrors()) {
            return ResponseEntity.badRequest().body(new ApiResponse(err.getFieldError().getDefaultMessage()));
        }

        if (service.updateInstructor(instructor)) {
            return ResponseEntity.ok(new ApiResponse("Instructor Updated Successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Instructor ID not Found"));

    }

    @DeleteMapping("/delete/{ID}")
    public ResponseEntity<?> deleteInstructor(@PathVariable String ID){
        if(service.deleteInstructor(ID)){
            return ResponseEntity.ok(new ApiResponse("Instructor Deleted Successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Instructor ID not found"));
    }

    @GetMapping("/get/specialization/{specialization}")
    public ResponseEntity<?> getInstructorsBySpecialization(@PathVariable String specialization){
        if(service.getInstructorsBySpecialization(specialization).isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponse("No Instructors With that specialization"));
        }

        return ResponseEntity.ok(service.getInstructorsBySpecialization(specialization));
    }

    @PostMapping("/assign/course/{courseID}/{instructorID}")
    public ResponseEntity<?> assignCourseToInstructor(@PathVariable String courseID,@PathVariable String instructorID){
        if(service.assignCourseToInstructor(courseID,instructorID))
            return ResponseEntity.ok(new ApiResponse("Assigned course to instructor"));
    return ResponseEntity.badRequest().body(new ApiResponse("Assigning failed"));
    }

    @GetMapping("/get/courses/{instructorID}")
    public ResponseEntity<?> getCourseTaught(@PathVariable String instructorID){
        if(service.getCoursesTaught(instructorID).isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponse("Instructor is not assigned to any courses"));
        }
        return ResponseEntity.ok(service.getCoursesTaught(instructorID));

    }

    @GetMapping("/get/is/teach/{instructorID}/{courseID}")
    public ResponseEntity<?> isInstructorTeachingCourse(@PathVariable String instructorID, @PathVariable String courseID){
        if(service.isInstructorTeachingCourse(instructorID,courseID)){
            return ResponseEntity.ok(new ApiResponse("Instructor is Teaching That course"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Teacher Is Not Teaching That Course"));
    }




}
