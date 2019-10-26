package com.njug.academic.controllers;

import com.njug.academic.models.Student;
import com.njug.academic.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/students")
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    @GetMapping
    public @ResponseBody List<Student> getAllStudents() {
        return studentsService.getAllStudents();
    }


}
