package com.njug.academic.controllers;

import com.njug.academic.models.Student;
import com.njug.academic.services.StudentsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentsController.class)
public class StudentsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentsService service;

    @Test
    public void givenOneStudentInDb_WhenGettingAllStudents_thenItReturnsOneStudent() throws Exception{
        Student student = new Student("John Doe", "En la cuidad de la furia");
        List<Student> allStudents = Arrays.asList(student);

        given(service.getAllStudents()).willReturn(allStudents);

        mvc.perform(get("/students")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value(hasSize(1)))
            .andExpect(jsonPath("$[0].fullName").value(student.getFullName()));

    }


}
