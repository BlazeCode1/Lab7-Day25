package org.example.lab7day25.Service;

import lombok.RequiredArgsConstructor;
import org.example.lab7day25.Model.Course;
import org.example.lab7day25.Model.Student;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

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
