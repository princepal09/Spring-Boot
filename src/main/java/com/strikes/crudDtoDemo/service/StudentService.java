package com.strikes.crudDtoDemo.service;

import com.strikes.crudDtoDemo.dto.StudentRequestDTO;
import com.strikes.crudDtoDemo.dto.StudentResponseDTO;
import com.strikes.crudDtoDemo.entity.Student;
import com.strikes.crudDtoDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentResponseDTO createStudent(StudentRequestDTO studentReq) {

        Student student = mapToEntity(studentReq);

        Student studentResp = studentRepository.save(student);

        return mapToDto(studentResp);
    }

    private Student mapToEntity(StudentRequestDTO studentReq) {

        Student student = new Student();

        student.setName(studentReq.getName());
        student.setAge(studentReq.getAge());
        student.setEmail(studentReq.getEmail());
        student.setRollNo(studentReq.getRollNo());
        student.setSubject(studentReq.getSubject());

        student.setDeleted(false);

        return student;
    }

    private StudentResponseDTO mapToDto(Student student) {

        StudentResponseDTO responseDTO = new StudentResponseDTO();

        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setAge(student.getAge());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setRollNo(student.getRollNo());
        responseDTO.setSubject(student.getSubject());
        responseDTO.setMessage("Student Saved Successfully!!");

        return responseDTO;
    }
}
