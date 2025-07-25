package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Api.ApiResponse;
import com.example.learningmanagementsystem.Model.TeacherModel;
import com.example.learningmanagementsystem.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping("/add")
    public ResponseEntity<?> addTeacher(@Valid @RequestBody TeacherModel teacher, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(msg));
        }
        teacherService.addTeacher(teacher);
        return ResponseEntity.ok(new ApiResponse("Teacher added successfully"));
    }

    @GetMapping("/get")
    public ArrayList<TeacherModel> getTeachers() {
        return teacherService.getAllTeachers();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable String id, @Valid @RequestBody TeacherModel teacher, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(msg));
        }

        boolean isUpdated = teacherService.updateTeacher(id, teacher);
        if (isUpdated) {
            return ResponseEntity.ok(new ApiResponse("Teacher updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Teacher not found!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable String id) {
        boolean isDeleted = teacherService.deleteTeacher(id);
        if (isDeleted) {
            return ResponseEntity.ok(new ApiResponse("Teacher deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Teacher not found!"));
    }



    @GetMapping("/get/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable String id) {
        TeacherModel teacher = teacherService.getTeacherById(id);
        if (teacher == null) {
            return ResponseEntity.status(404).body(new ApiResponse("Teacher not found"));
        }
        return ResponseEntity.ok(teacher);
    }


    @GetMapping("/over-age/{age}")
    public ArrayList<TeacherModel> getTeachersAboveAge(@PathVariable int age) {
        return teacherService.getTeachersAboveAge(age);
    }


    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<?> existsByName(@PathVariable String name) {
        boolean exists = teacherService.existsByName(name);
        return ResponseEntity.ok(new ApiResponse("Exists: " + exists));
    }

 
    @GetMapping("/count")
    public ResponseEntity<?> countTeachers() {
        int count = teacherService.countTeachers();
        return ResponseEntity.ok(new ApiResponse("Total teachers: " + count));
    }
}

