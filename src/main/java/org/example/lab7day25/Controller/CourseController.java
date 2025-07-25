package org.example.lab7day25.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.lab7day25.Api.ApiResponse;
import org.example.lab7day25.Model.Course;
import org.example.lab7day25.Service.CourseService;
import org.example.lab7day25.Service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
public class CourseController {

        private final CourseService service;
        private final EnrollmentService enrollmentService;


        @GetMapping("/get")
        public ResponseEntity<?> getCourse(){
            if(service.getCourses().isEmpty()){
                return ResponseEntity.badRequest().body(new ApiResponse("Courses List Is Empty"));
            }
            return ResponseEntity.ok(service.getCourses());
        }


        @PostMapping("/add")
        public ResponseEntity<?> addCourse(@Valid @RequestBody Course course, Errors err){
            if(err.hasErrors()){
                 return ResponseEntity.badRequest().body(new ApiResponse(err.getFieldError().getDefaultMessage()));
            }
            if(service.addCourse(course)){
                return ResponseEntity.ok(new ApiResponse("Course Added Successfully"));
            }
                return ResponseEntity.badRequest().body(new ApiResponse("Course Already Added"));
        }


        @PutMapping("/update")
        public ResponseEntity<?> updateCourse(@Valid @RequestBody Course course,Errors err) {
            if (err.hasErrors()) {
                return ResponseEntity.badRequest().body(new ApiResponse(err.getFieldError().getDefaultMessage()));
            }

            if (service.updateCourse(course)) {
                return ResponseEntity.ok(new ApiResponse("Course Updated Successfully"));
            }
            return ResponseEntity.badRequest().body(new ApiResponse("Course ID not Found"));

        }

        @DeleteMapping("/delete/{ID}")
        public ResponseEntity<?> deleteCourse(@PathVariable String ID){
            if(service.deleteCourse(ID)){
                return ResponseEntity.ok(new ApiResponse("Course Deleted Successfully"));
            }
            return ResponseEntity.badRequest().body(new ApiResponse("Course ID not found"));
        }

        @GetMapping("/get/available")
        public ResponseEntity<?> getAvailableCourses(){
            if(service.getAvailableCourses().isEmpty()){
                return ResponseEntity.badRequest().body(new ApiResponse("No Available Courses, All Full"));

            }
            return ResponseEntity.ok(service.getAvailableCourses());
        }

        @GetMapping("/get/title/{title}")
        public ResponseEntity<?> getCoursesByTitle(@PathVariable String title){
            if(service.getCoursesByTitle(title).isEmpty()){
                return ResponseEntity.badRequest().body(new ApiResponse("Course Not found"));

            }
            return ResponseEntity.ok(service.getCoursesByTitle(title));
        }

        @GetMapping("/get/enrolled/{courseID}")
        public ResponseEntity<?> getEnrolledStudents(@PathVariable String courseID){
            if(enrollmentService.getEnrolledStudents(courseID).isEmpty())
                return ResponseEntity.badRequest().body(new ApiResponse("No enrolled Students"));
        return ResponseEntity.ok(enrollmentService.getEnrolledStudents(courseID));

        }

        @GetMapping("/enrollment/count/{courseID}")
        public ResponseEntity<?> getEnrollmentCount(@PathVariable String courseID){
            return ResponseEntity.ok(enrollmentService.getEnrollmentCount(courseID));
        }



}
