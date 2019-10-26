package com.njug.academic.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "students")
public class Student {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    public Student() {}

    /**
     * Constructor de conveniencia para facilitar inicializacion.
     * @param name el nombre del estudiante
     * @param address la direccion.
     */
    public Student(final String name, final String address) {
        this.fullName = name;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
