package com.faiz.demo;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;

@Data
@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student_db")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private String fatherName;
    private Long phoneNumber;
    private String className;
    private String address;
    @Lob
    @Column(name = "Image", length = 1000)
    private Blob photo;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", className='" + className + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
