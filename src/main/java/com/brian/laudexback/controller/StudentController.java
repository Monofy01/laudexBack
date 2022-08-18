package com.brian.laudexback.controller;


import com.brian.laudexback.model.Student;
import com.brian.laudexback.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import org.slf4j.Logger;

import javax.lang.model.type.NullType;
import java.util.List;

@RestController
@RequestMapping("/laudex")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ApiOperation(value = "Buscar todos los ESTUDIANTES", notes = "No se require ingresar nada sobre el body content del request")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Student.class, responseContainer = "List")})
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> studentList = studentService.findAllStudent();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar ESTUDIANTE por ID", notes = "Se requiere ingresar por Path el id del estudiante para realizar busqueda")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Student.class)})
    @GetMapping("/find/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        Student student = studentService.findStudentsById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @ApiOperation(value = "Agregar ESTUDIANTE", notes = "Se requiere ingresar modelo Student para hacer insercion correctamente")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK. El recurso se agrega correctamente", response = Student.class)})
    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student newStudent = studentService.addStudent(student);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualizar ESTUDIANTE", notes = "Se requiere ingresar modelo Student para hacer update correctamente, se necesita ingresar el campo ID")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK. El recurso se actualizo correctamente", response = Student.class)})
    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updateStudent = studentService.updateStudent(student);
        return new ResponseEntity<>(updateStudent, HttpStatus.OK);
    }

    @ApiOperation(value = "Borrar ESTUDIANTE", notes = "Se requiere ingresar por Path el id del estudiante para realizar borrado")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK. El recurso se borro correctamente", response = String.class)})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteByStudentId(id);
        return new ResponseEntity<String>("Se ha borrado correctamente", HttpStatus.OK);
    }
}
