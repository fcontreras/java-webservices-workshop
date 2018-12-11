package com.njug.academic.services;

import com.njug.academic.models.Student;
import com.njug.academic.respositories.StudentsRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class StudentsServiceTest {

    // Una anotacion conveniente que ayuda a declarar @Beans que tienen un scope limitado
    // logrando asi que no sean tomados por otras clases al momento de ejecutar.
    @TestConfiguration
    static class StudentServiceTestConfiguration {
        @Bean
        public StudentsService studentsService() {
            return new StudentsService();
        }
    }

    @Autowired
    private StudentsService studentsService;

    // El repositorio debera ser un mock para asi definir los resultados que debemos esperar
    // para que nuestros tests corran en un entorno controlado.
    @MockBean
    private StudentsRepository studentsRepository;

    // GIVEN
    // Configuracion inicial de todos las pruebas que se van a ejecutar en esta clase.
    @Before
    public void setUp() {
        List<Student> students = Lists.newArrayList(new Student("John Doe", "En algun lugar de un gran pais"));
        Mockito.when(studentsRepository.findAll())
                .thenReturn(students);
    }

    @Test
    public void whenThereIsOneStudentInDb_thenAStudentShouldBeReturned() {
        List<Student> results = studentsService.getAllStudents();

        assertThat(results.size()).isEqualTo(1);
        assertThat(results.get(0).getFullName()).isEqualToIgnoringCase("john doe");
    }



}
