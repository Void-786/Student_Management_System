package com.faiz.demo.service;

import com.faiz.demo.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public interface IStudentService {
    public Student saveStudent(Student student);
    public List<Student> getAllStudents();
    public Student getStudentById(Integer id);
    public void deleteStudentById(Integer id);
    public void updateStudentById(Integer Id, Student student);
    public void uploadFile(MultipartFile file, Integer Id) throws IOException, SQLException;

    public byte[] downloadFile(Integer id) throws SQLException;
}
