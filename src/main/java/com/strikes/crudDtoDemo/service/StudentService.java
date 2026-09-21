package com.strikes.crudDtoDemo.service;

import com.strikes.crudDtoDemo.dto.CreateStudentRequestDTO;
import com.strikes.crudDtoDemo.dto.CreateStudentResponseDTO;
import com.strikes.crudDtoDemo.dto.UpdateStudentRequestDTO;
import com.strikes.crudDtoDemo.dto.UpdateStudentResponseDTO;
import com.strikes.crudDtoDemo.entity.Student;
import com.strikes.crudDtoDemo.exception.DuplicateResourceException;
import com.strikes.crudDtoDemo.exception.ResourceNotFoundException;
import com.strikes.crudDtoDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDTO createStudent(
            CreateStudentRequestDTO studentReq) {

        Student student = mapToEntity(studentReq);

        if(emailExists(student)){
            throw new DuplicateResourceException("Student with email" + student.getEmail() + "already exists");
        }

        Student savedStudent = studentRepository.save(student);

        return mapToDto(savedStudent);
    }


    public void deleteStudent(UUID id){
       Student studentToBeDeleted = studentRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Student with id" + id + "not found"));

       studentRepository.delete(studentToBeDeleted);


    }

    public CreateStudentResponseDTO getStudent(UUID id) {

       Student studenResp = studentRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Student with id" + id + "not found"));

       return mapToDto(studenResp);
    }

    public void deleteStudentSoftly(UUID id ){
        Student studentToBeDelted = studentRepository.findByIdAndDeletedIsFalse(id).orElseThrow(() -> new ResourceNotFoundException("Student with this this id" + id + "not found"));

        studentToBeDelted.setDeleted(true);
        studentRepository.save(studentToBeDelted);
    }

    public UpdateStudentResponseDTO updateStudent(
            UUID id,
            UpdateStudentRequestDTO studentReq) {

        Student existingStudent =
                studentRepository.findByIdAndDeletedIsFalse(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Student with id " + "not found"));


        existingStudent.setName(studentReq.getName());
        existingStudent.setRollNo(studentReq.getRollNo());
        existingStudent.setSubject(studentReq.getSubject());
        existingStudent.setAge(studentReq.getAge());

        Student savedStudent = studentRepository.save(existingStudent);

        return mapToUpdateDto(savedStudent);
    }

    public List<CreateStudentResponseDTO> getAllStudents() {

        List<Student> students =
                studentRepository.findAllByDeletedFalse();

        return mapToDto(students);
    }

    private List<CreateStudentResponseDTO> mapToDto(List<Student> students) {

        return students.stream()
                .map(this::mapToDto)
                .toList();
    }

    private Student mapToEntity(CreateStudentRequestDTO studentReq) {

        Student student = new Student();

        student.setName(studentReq.getName());
        student.setAge(studentReq.getAge());
        student.setEmail(studentReq.getEmail());
        student.setRollNo(studentReq.getRollNo());
        student.setSubject(studentReq.getSubject());
        student.setDeleted(false);

        return student;
    }

    private CreateStudentResponseDTO mapToDto(Student student) {

        CreateStudentResponseDTO responseDTO =
                new CreateStudentResponseDTO();

        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setAge(student.getAge());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setRollNo(student.getRollNo());
        responseDTO.setSubject(student.getSubject());
        responseDTO.setCreatedAt(student.getCreatedAt());
        responseDTO.setUpdatedAt(student.getUpdatedAt());
        responseDTO.setMessage("Student Saved Successfully!!");

        return responseDTO;
    }

    private UpdateStudentResponseDTO mapToUpdateDto(Student student) {

        UpdateStudentResponseDTO responseDTO =
                new UpdateStudentResponseDTO();

        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setAge(student.getAge());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setRollNo(student.getRollNo());
        responseDTO.setSubject(student.getSubject());
        responseDTO.setMessage("Student updated Successfully!!");

        return responseDTO;
    }

    private boolean emailExists(Student student){
        return studentRepository.existsByEmail(student.getEmail());
    }
}