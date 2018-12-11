package com.njug.academic.respositories;

import com.njug.academic.models.Student;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Sirve para configurar Spring con JUnit
@RunWith(SpringRunner.class)
// Esta anotacion ayuda con:
// - Configurar H2 como origin de datos en memoria.
// - Configurar Hibernate, Spring data y demas.
// - Realizar el @EntityScan
// - Encender los logs de SQL
@DataJpaTest
public class StudentsRepositoryTest {

    /**
     * Se usa para crear el escenario de pruebas
     */
    @Autowired
    private TestEntityManager entityManager;

    /**
     * El repositorio a probar
     */
    @Autowired
    private StudentsRepository studentsRepository;

    @Test
    public void givenAStudentInDB_whenGetAllStudents_returnOneStudent() {
        //given
        Student student = new Student();
        student.setFullName("My test student");
        student.setAddress("Thi si my home");
        entityManager.persist(student);
        entityManager.flush();

        //when
        List<Student> students = Lists.newArrayList(studentsRepository.findAll());

        //then
        assertThat(students.size()).isEqualTo(1);
        assertThat(students.get(0).getId()).isEqualTo(1);
    }


}
