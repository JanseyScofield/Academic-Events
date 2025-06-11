package com.scofield.app.services.person;

import java.io.IOException;

import com.scofield.app.domains.abstracts.Repository;
import com.scofield.app.domains.person.PersonRepository;
import com.scofield.app.domains.person.Student;
import com.scofield.app.services.abstratcs.Service;

public class StudentService extends Service<Student>{
    public StudentService(PersonRepository repository ){
        super(repository);
    }

    public Student register(String name, String cpf, String registerNumber){
        try{
            Student newStudent = new Student(name,  cpf, registerNumber);
            repository.register(newStudent);
            return newStudent;
        }catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("Error to register an student: " + ex.getMessage());
        }
        // catch (IOException ex){
        //     throw new RuntimeException("Internal server error: " + ex.getMessage());
        // }
        
    }
}