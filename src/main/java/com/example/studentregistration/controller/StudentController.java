
package com.example.studentregistration.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentregistration.model.Student;
import com.example.studentregistration.repository.StudentRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Register a student
    @PostMapping
    public Student registerStudent(@Valid @RequestBody Student student) {
        return studentRepository.save(student);
    }

    // View all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Find student by ID
    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException(
                        "Student not found with ID: " + studentId));
    }

    // Delete registration
    @DeleteMapping("/{studentId}")
    public String deleteStudent(@PathVariable Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new RuntimeException(
                    "Student not found with ID: " + studentId);
        }

        studentRepository.deleteById(studentId);
        return "Student registration deleted successfully";
    }
}
