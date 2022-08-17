package com.brian.laudexback.service;

import com.brian.laudexback.exceptions.StudentNotFoundException;
import com.brian.laudexback.model.Student;
import com.brian.laudexback.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //add
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    //delete
    public void deleteByStudentId(Long id) {
        studentRepository.deleteStudentById(id);
    }

    //update
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    //all
    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }

    //findbyID
    public Student findStudentsById(Long id) {
        return studentRepository.findStudentsById(id).orElseThrow(() -> new StudentNotFoundException("Student was not found"));
    }


}


