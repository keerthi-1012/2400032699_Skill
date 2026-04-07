package com.example.courseapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.courseapi.model.Course;

@Service
public class CourseService {

    List<Course> courses = new ArrayList<>();

    public List<Course> getAllCourses() {
        return courses;
    }

    public Course addCourse(Course course) {
        courses.add(course);
        return course;
    }

    public Course updateCourse(int id, Course updatedCourse) {

        for (Course c : courses) {
            if (c.getCourseId() == id) {
                c.setTitle(updatedCourse.getTitle());
                c.setDuration(updatedCourse.getDuration());
                c.setFee(updatedCourse.getFee());
                return c;
            }
        }

        return null;
    }

    public boolean deleteCourse(int id) {

        return courses.removeIf(c -> c.getCourseId() == id);
    }

    public List<Course> searchCourse(String title) {

        List<Course> result = new ArrayList<>();

        for (Course c : courses) {
            if (c.getTitle().equalsIgnoreCase(title)) {
                result.add(c);
            }
        }

        return result;
    }
}