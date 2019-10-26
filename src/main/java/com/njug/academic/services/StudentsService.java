package com.njug.academic.services;

import com.njug.academic.models.Student;
import com.njug.academic.respositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class StudentsService {

    @Autowired
    StudentsRepository studentsRepository;

    /**
     * @return Devuelve una lista con todos los estudiantes.
     */
    public List<Student> getAllStudents() {
        List<Student> result = new LinkedList<>();
        studentsRepository.findAll().forEach(result::add);
        return result;
    }
}
