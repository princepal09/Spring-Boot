package com.strikes.crudDtoDemo.controller;

import com.strikes.crudDtoDemo.dto.CreateStudentRequestDTO;
import com.strikes.crudDtoDemo.dto.CreateStudentResponseDTO;
import com.strikes.crudDtoDemo.dto.UpdateStudentRequestDTO;
import com.strikes.crudDtoDemo.dto.UpdateStudentResponseDTO;
import com.strikes.crudDtoDemo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;



//validataions   --> spring boot starter validations




@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateStudentResponseDTO> createStudent( @Valid
            @RequestBody CreateStudentRequestDTO studentRequestDto) {

        CreateStudentResponseDTO studentResp =
                studentService.createStudent(studentRequestDto);

        return ResponseEntity.status(201).body(studentResp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDTO> getStudent(
            @PathVariable UUID id) {

        CreateStudentResponseDTO studentResp =
                studentService.getStudent(id);

        if (studentResp == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentResp);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateStudentResponseDTO> updateStudent(
            @PathVariable UUID id,
            @RequestBody UpdateStudentRequestDTO studentReq) {

        UpdateStudentResponseDTO studentResp =
                studentService.updateStudent(id, studentReq);

        if (studentResp == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentResp);
    }

    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDTO>> getAllStudents() {

        List<CreateStudentResponseDTO> students =
                studentService.getAllStudents();

        return ResponseEntity.ok(students);
    }
}