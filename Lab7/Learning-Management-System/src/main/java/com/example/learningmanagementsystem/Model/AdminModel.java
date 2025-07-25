package com.example.learningmanagementsystem.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminModel {

    @NotEmpty(message = "ID is required!")
    private String id;

    @NotEmpty(message = "Name is required!")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters!")
    private String name;

    @NotEmpty(message = "Age is required!")
    @Min(value = 18, message = "Age must be at least 18!")
    private Integer age;
}
