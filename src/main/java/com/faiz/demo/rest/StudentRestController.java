package com.faiz.demo.rest;

import com.faiz.demo.Student;
import com.faiz.demo.dao.StudentRepo;
import com.faiz.demo.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StudentRestController {

    @Autowired
    private StudentRepo repo;

    @Autowired
    private IStudentService service;

    @PostMapping(value = "/Student")
    public Student resgisterStudent(@RequestBody Student student) throws IOException, SQLException {
        System.out.println("Student data: " + student);
        return service.saveStudent(student);
    }

    @GetMapping("/Student")
    public List<Student> getAllStudents() {
        List<Student> list = service.getAllStudents();
        return list;
    }

    @GetMapping("/Student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        Student stu = service.getStudentById(id);
        return new ResponseEntity<>(stu, HttpStatus.CREATED);
    }

    @PutMapping("/Student/{id}")
    public ResponseEntity<Student> updateStudentById(@PathVariable Integer id, @RequestBody Student student) {
        service.updateStudentById(id, student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/Student/{id}")
    public ResponseEntity<Student> deleteStudentById(@PathVariable Integer id) {
//        return new ResponseEntity<>(new Student, HttpStatus.OK);
        service.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/Student/photo/{id}")
    public String uploadStudentPhotoById(@RequestParam("photo") MultipartFile file, @PathVariable Integer id) throws SQLException, IOException {
        service.uploadFile(file, id);
        return "photo upload successfully";
    }

    @GetMapping("/Student/photo/{id}")
    public void downloadFile(@PathVariable Integer id) throws SQLException {
        service.downloadFile(id);
    }
}
