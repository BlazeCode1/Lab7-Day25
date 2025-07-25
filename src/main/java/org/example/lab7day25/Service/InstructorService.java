package org.example.lab7day25.Service;

import lombok.RequiredArgsConstructor;
import org.example.lab7day25.Model.Course;
import org.example.lab7day25.Model.Instructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class InstructorService {

    ArrayList<Instructor> instructors = new ArrayList<>();

    private final CourseService courseService;


    public ArrayList<Instructor> getInstructors(){
        return instructors;
    }

    public boolean addInstructor(Instructor instructor){
        for (Instructor i : instructors){
            if(i.getId().equals(instructor.getId())){
                return false;
            }
        }
        instructors.add(instructor);
        return true;
    }

    public boolean updateInstructor(Instructor instructor){
        for (Instructor i: instructors){
            if(instructor.getId().equals(i.getId())){
                instructors.set(instructors.indexOf(i),instructor);
                return true;
            }
        }
        return false;
    }


    public boolean deleteInstructor(String ID){
        for (Instructor i : instructors){
            if(ID.equals(i.getId())){
                instructors.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Instructor> getInstructorsBySpecialization(String specialization){
        ArrayList<Instructor> filteredInstructors = new ArrayList<>();
        for (Instructor i : instructors){
            if(specialization.equalsIgnoreCase(i.getSpecialization())){
                filteredInstructors.add(i);
            }
        }
        return filteredInstructors;
    }

    public boolean assignCourseToInstructor(String courseID, String instructorID){
        for(Course c : courseService.getCourses()){
            if(c.getId().equals(courseID)){
                c.setInstructorId(instructorID);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Course> getCoursesTaught(String instructorID){
        ArrayList<Course> taught = new ArrayList<>();
        for (Course c : courseService.getCourses()){
            if(c.getInstructorId().equals(instructorID)){
                taught.add(c);
            }
        }
        return taught;
    }

    public boolean isInstructorTeachingCourse(String instructorID, String courseID){
        for (Course c : courseService.getCourses()){
            if(c.getId().equals(courseID) && c.getInstructorId().equals(instructorID)){
                return true;
            }
        }
        return false;
    }







}
