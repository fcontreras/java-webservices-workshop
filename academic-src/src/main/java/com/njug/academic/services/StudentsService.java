package com.njug.academic.services;

import com.njug.academic.models.Student;
import com.njug.academic.respositories.StudentsRepository;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsService {

    @Autowired
    StudentsRepository studentsRepository;

    /**
     * @return Devuelve una lista con todos los estudiantes.
     */
    public List<Student> getAllStudents() {
        return Lists.newArrayList(studentsRepository.findAll());
    }
}
