package com.faiz.demo.service;

import com.faiz.demo.Student;
import com.faiz.demo.dao.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepo repo;

    @Override
    public Student saveStudent(Student student) {
        repo.save(student);

        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    @Override
    public Student getStudentById(Integer id) {
        Optional<Student> optional = repo.findById(id);
        if (optional.isPresent()) return optional.get();
        else throw new NoSuchElementException("Student with Id " + id + "not found");
    }

    @Override
    public void deleteStudentById(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public void updateStudentById(Integer id, Student student) {
        Optional<Student> studentOptional = repo.findById(id);
        if (studentOptional.isPresent()) {
            Student st = studentOptional.get();
            st.setName(student.getName());
            st.setClassName(student.getClassName());
            st.setAddress(student.getAddress());
            st.setEmail(student.getEmail());
            st.setFatherName(student.getFatherName());
            st.setPhoneNumber(student.getPhoneNumber());
            repo.save(st);
        } else {
            throw new NoSuchElementException("Student with Id " + id + " not found");
        }
    }

    @Override
    public void uploadFile(MultipartFile file, Integer id) throws IOException, SQLException {
        Optional<Student> studentOptional = repo.findById(id);
        if (studentOptional.isPresent()) {
            Student st = studentOptional.get();
            byte[] photoBytes = file.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            st.setPhoto(photoBlob);
            repo.save(st);
        }
    }

    @Override
    public byte[] downloadFile(Integer id) throws SQLException {
        Optional<Student> student = repo.findById(id);
        Blob photoBlob = student.get().getPhoto();
        if(photoBlob != null) {
            return photoBlob.getBytes(1, (int) photoBlob.length());
        }
        return null;
    }
}
