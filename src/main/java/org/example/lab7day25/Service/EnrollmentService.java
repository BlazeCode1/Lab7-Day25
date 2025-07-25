package org.example.lab7day25.Service;

import lombok.RequiredArgsConstructor;
import org.example.lab7day25.Model.Course;
import org.example.lab7day25.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final StudentService studentService;
    private final CourseService courseService;

    public int enrollCourse(String courseID, String studentID) {
        ArrayList<Course> courses = courseService.getCourses();
        ArrayList<Student> students = studentService.getStudents();

        if (courses.isEmpty()) return 0; // No courses
        if (students.isEmpty()) return 1; // No students

        Course targetCourse = null;
        for (Course c : courses) {
            if (courseID.equals(c.getId())) {
                if (c.getCapacity() >= 100) return 2; // Full
                targetCourse = c;
                break;
            }
        }

        if (targetCourse == null) return 4; // Course not found

        for (Student s : students) {
            if (studentID.equals(s.getId())) {
                if (s.getEnrolledCourses().contains(targetCourse)) return 6; // Already enrolled

                s.getEnrolledCourses().add(targetCourse);
                targetCourse.setCapacity(targetCourse.getCapacity() + 1);
                return 3; // Success
            }
        }

        return 5; // Student not found
    }

    public ArrayList<Student> getEnrolledStudents(String courseID) {
        ArrayList<Student> list = new ArrayList<>();
        for (Student s : studentService.getStudents()) {
            for (Course c : s.getEnrolledCourses()) {
                if (c.getId().equals(courseID)) {
                    list.add(s);
                    break;
                }
            }
        }
        return list;
    }

    public int getEnrollmentCount(String courseID) {
        return getEnrolledStudents(courseID).size();
    }
}

