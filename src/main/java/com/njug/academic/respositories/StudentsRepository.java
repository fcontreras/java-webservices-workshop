package com.njug.academic.respositories;

import com.njug.academic.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentsRepository extends CrudRepository<Student, Integer> {
}
