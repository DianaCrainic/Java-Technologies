package com.uaic.lab3.daos;

import com.uaic.lab3.dtos.StudentDto;
import com.uaic.lab3.entities.Student;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class StudentDao extends Dao<Student> {
    public Student getById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    public List getAll() {
        return entityManager.createNamedQuery("Student.getAll").getResultList();
    }

    public void create(StudentDto studentDto) {
        String name = studentDto.getName();
        String assignedExams = studentDto.getAssignedExams();
        Student student = new Student(name, assignedExams);
        entityManager.persist(student);
        entityManager.flush();
    }

    public void update(Integer id, StudentDto studentDto) {
        Student student = this.getById(id);
        student.setName(studentDto.getName());
        student.setAssignedExams(studentDto.getAssignedExams());
        entityManager.merge(student);
        entityManager.flush();
    }
}
