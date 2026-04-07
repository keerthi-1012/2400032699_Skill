package com.example.courseapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.courseapi.model.Course;
import com.example.courseapi.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    // Add a new course
    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course savedCourse = service.addCourse(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    // Get all courses
    @GetMapping
    public ResponseEntity<List<Course>> getCourses() {
        return new ResponseEntity<>(service.getAllCourses(), HttpStatus.OK);
    }

    // Get course by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable int id) {

        for (Course c : service.getAllCourses()) {
            if (c.getCourseId() == id) {
                return new ResponseEntity<>(c, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
    }

    // Update course
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable int id, @RequestBody Course course) {

        Course updated = service.updateCourse(id, course);

        if (updated == null) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // Delete course
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {

        boolean deleted = service.deleteCourse(id);

        if (!deleted) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
    }

    // Search course by title
    @GetMapping("/search/{title}")
    public ResponseEntity<List<Course>> searchCourse(@PathVariable String title) {
        return new ResponseEntity<>(service.searchCourse(title), HttpStatus.OK);
    }
}