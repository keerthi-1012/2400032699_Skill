package com.example.studentapp.controller;

import com.example.studentapp.model.Student;
import com.example.studentapp.exception.StudentNotFoundException;
import com.example.studentapp.exception.InvalidInputException;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id) {

        int studentId;

        try {
            studentId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid input format. Student ID must be a number.");
        }

        if (studentId != 1) {
            throw new StudentNotFoundException("Student with ID " + studentId + " not found.");
        }

        return new Student(2, "Keerthi", "Computer Science");
    }
}