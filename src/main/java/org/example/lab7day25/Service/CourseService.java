package org.example.lab7day25.Service;

import lombok.RequiredArgsConstructor;
import org.example.lab7day25.Model.Course;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CourseService {

    ArrayList<Course> courses = new ArrayList<>();

    public ArrayList<Course> getCourses(){
        return courses;
    }

    public boolean addCourse(Course course){
        for (Course c : courses){
            if(c.getId().equals(course.getId())){
                return false;// ID Already Added
            }
        }
        courses.add(course);
        return true;//Course Added Successfully
    }

    public boolean updateCourse(Course course){
        for (Course c: courses){
            if(course.getId().equals(c.getId())){
                courses.set(courses.indexOf(c),course);
                return true;//Course Updated Successfully
            }
        }
        return false;//Course With Given ID Not Found
    }


    public boolean deleteCourse(String ID){
        for (Course c : courses){
            if(ID.equals(c.getId())){
                courses.remove(c);
                return true;//Course Removed Successfully
            }
        }
        return false;//Course Not Found, though not Deleted
    }


    public ArrayList<Course> getAvailableCourses(){
        ArrayList<Course> available = new ArrayList<>();
        for(Course c : courses){
            if(c.getCapacity() < 100){
                available.add(c);
            }
        }
        return available;
    }

    public ArrayList<Course> getCoursesByTitle(String title){
        ArrayList<Course> result = new ArrayList<>();
        for(Course c : courses){
            if(c.getTitle().toLowerCase().contains(title.toLowerCase())){
                result.add(c);
            }
        }
        return result;
    }









}
