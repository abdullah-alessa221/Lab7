package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Api.ApiResponse;
import com.example.learningmanagementsystem.Model.AdminModel;
import com.example.learningmanagementsystem.Service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/add")
    public ResponseEntity<?> addAdmin(@Valid @RequestBody AdminModel admin, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(msg));
        }
        adminService.addAdmin(admin);
        return ResponseEntity.ok(new ApiResponse("Admin added successfully"));
    }

    @GetMapping("/get")
    public ArrayList<AdminModel> getAdmins() {
        return adminService.getAllAdmins();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable String id, @Valid @RequestBody AdminModel admin, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(msg));
        }

        boolean isUpdated = adminService.updateAdmin(id, admin);
        if (isUpdated) {
            return ResponseEntity.ok(new ApiResponse("Admin updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Admin not found!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable String id) {
        boolean isDeleted = adminService.deleteAdmin(id);
        if (isDeleted) {
            return ResponseEntity.ok(new ApiResponse("Admin deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Admin not found!"));
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable String id) {
        AdminModel admin = adminService.getAdminById(id);
        if (admin == null) {
            return ResponseEntity.status(404).body(new ApiResponse("Admin not found"));
        }
        return ResponseEntity.ok(admin);
    }


    @GetMapping("/by-name/{name}")
    public ArrayList<AdminModel> getAdminsByName(@PathVariable String name) {
        return adminService.getAdminsByName(name);
    }


    @GetMapping("/over-age/{age}")
    public ArrayList<AdminModel> getAdminsOverAge(@PathVariable int age) {
        return adminService.getAdminsOverAge(age);
    }


    @GetMapping("/count")
    public ResponseEntity<?> countAdmins() {
        int count = adminService.countAdmins();
        return ResponseEntity.ok(new ApiResponse("Total admins: " + count));
    }
}