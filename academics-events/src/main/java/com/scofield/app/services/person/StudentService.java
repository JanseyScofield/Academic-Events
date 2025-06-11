package com.scofield.app.services.person;


import com.scofield.app.domains.person.PersonRepository;
import com.scofield.app.domains.person.Student;
import com.scofield.app.services.abstratcs.Service;

public class StudentService extends Service<Student>{
    public StudentService(PersonRepository repository ){
        super(repository);
    }

    @Override
    public Student register(String name, String cpf, Object registerNumber){
        try{
            if(!(registerNumber instanceof String)){
                throw new IllegalArgumentException("To register an student, register number must be an string");
            }
            Student newStudent = new Student(name,  cpf, (String)registerNumber);
            repository.register(newStudent);
            return newStudent;
        }catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("Error to register an student: " + ex.getMessage());
        }
        
    }
}