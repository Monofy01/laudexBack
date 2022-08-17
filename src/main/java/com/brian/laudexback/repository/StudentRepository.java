package com.brian.laudexback.repository;

import com.brian.laudexback.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Transactional
    void deleteStudentById(Long id);


    Optional<Student> findStudentsById(Long id);

}
