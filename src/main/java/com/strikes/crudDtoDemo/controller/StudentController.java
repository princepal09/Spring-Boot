package com.strikes.crudDtoDemo.controller;

import com.strikes.crudDtoDemo.dto.StudentRequestDTO;
import com.strikes.crudDtoDemo.dto.StudentResponseDTO;
import com.strikes.crudDtoDemo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Create
    @PostMapping("/create")
    public ResponseEntity<StudentResponseDTO> createStudent(
            @RequestBody StudentRequestDTO studentRequestDto) {

        StudentResponseDTO studentResp =
                studentService.createStudent(studentRequestDto);

        return ResponseEntity.ok(studentResp);
    }

    // Read

    // Update

    // Delete
}