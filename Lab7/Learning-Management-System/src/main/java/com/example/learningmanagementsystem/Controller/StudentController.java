package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Api.ApiResponse;
import com.example.learningmanagementsystem.Model.StudentModel;
import com.example.learningmanagementsystem.Service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@Valid @RequestBody StudentModel student, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(msg));
        }
        studentService.addStudent(student);
        return ResponseEntity.ok(new ApiResponse("Student added successfully"));
    }

    @GetMapping("/get")
    public ArrayList<StudentModel> getStudents() {
        return studentService.getAllStudents();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String id, @Valid @RequestBody StudentModel student, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(msg));
        }

        boolean isUpdated = studentService.updateStudent(id, student);
        if (isUpdated) {
            return ResponseEntity.ok(new ApiResponse("Student updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student not found!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id) {
        boolean isDeleted = studentService.deleteStudent(id);
        if (isDeleted) {
            return ResponseEntity.ok(new ApiResponse("Student deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student not found!"));
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable String id) {
        StudentModel student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.status(404).body(new ApiResponse("Student not found"));
        }
        return ResponseEntity.ok(student);
    }


    @GetMapping("/over-age/{age}")
    public ArrayList<StudentModel> getStudentsUnderAge(@PathVariable int age) {
        return studentService.getStudentsUnderAge(age);
    }


    @GetMapping("/search/{name}")
    public ArrayList<StudentModel> searchByName(@PathVariable String name) {
        return studentService.searchByName(name);
    }


    @GetMapping("/count")
    public ResponseEntity<?> countStudents() {
        int count = studentService.countStudents();
        return ResponseEntity.ok(new ApiResponse("Total students: " + count));
    }


}
