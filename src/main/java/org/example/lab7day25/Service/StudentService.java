package org.example.lab7day25.Service;

import lombok.RequiredArgsConstructor;
import org.example.lab7day25.Model.Course;
import org.example.lab7day25.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final CourseService courseService;

    ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents(){
    return students;
    }

    public boolean addStudent(Student student){
        for (Student s : students){
            if(s.getId().equals(student.getId())){
                return false;
            }
        }
        students.add(student);
        return true;
    }

    public boolean updateStudent(Student student){
        for (Student s: students){
            if(student.getId().equals(s.getId())){
                students.set(students.indexOf(s),student);
                return true;
            }
        }
        return false;
    }


    public boolean deleteStudent(String ID){
        for (Student s : students){
            if(ID.equals(s.getId())){
                students.remove(s);
                return true;
            }
        }
        return false;
    }
    // First Method
    public ArrayList<ArrayList<Course>> getStudentCourses(String ID){
       ArrayList<ArrayList<Course>> studentCourses = new ArrayList<>();
        if(students.isEmpty()){
            return null; // No students
        }

        for (Student s : students){
            if(s.getId().equals(ID)){

                studentCourses.add(s.getEnrolledCourses());
                break; // courses found courses
            }
        }
        return studentCourses;
    }

    // second method
    public int enrollCourse(String courseID, String studentID) {
        if (courseService.getCourses().isEmpty()) return 0; // No courses
        if (students.isEmpty()) return 1;                    // No students

        Course targetCourse = null;

        for (Course c : courseService.getCourses()) {
            if (courseID.equals(c.getId())) {
                if (c.getCapacity() >= 100) return 2; // Course full
                targetCourse = c;
                break;
            }
        }

        if (targetCourse == null) return 4; // Course not found

        for (Student s : students) {
            if (studentID.equals(s.getId())) {
                List<Course> enrolled = s.getEnrolledCourses();
                if (enrolled.contains(targetCourse)) return 6; // Already enrolled

                enrolled.add(targetCourse);
                targetCourse.setCapacity(targetCourse.getCapacity() + 1); // Increase capacity
                return 3; // Success
            }
        }

        return 5; // Student not found
    }

    public ArrayList<Student> getStudentsByMajor(String major){
        ArrayList<Student> sameMajor = new ArrayList<>();
        for (Student s:students){
            if(s.getMajor().equalsIgnoreCase(major)){
                sameMajor.add(s);
            }
        }
        return sameMajor;
    }

    public ArrayList<Student> getStudentsByGPA(double GPA){
        ArrayList<Student> filteredStudents = new ArrayList<>();
        for (Student s: students){
            if(s.getGPA() >= GPA){
                filteredStudents.add(s);
            }
        }
        return filteredStudents;
    }



}
