package com.strikes.crudDtoDemo.repository;

import com.strikes.crudDtoDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByIdAndDeletedIsFalse(UUID id);
    List<Student> findAllByDeletedFalse();
}
